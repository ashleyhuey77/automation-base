package com.warnermedia.data.loki;


import org.apache.commons.lang3.ClassUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockValuesService {
	private static final Pattern EXPRESSION_PATTERN = Pattern.compile("#\\{([a-z0-9A-Z_.]+)\\s?(?:'([^']+)')?(?:,'([^']+)')*\\}");

    private final Logger log = Logger.getLogger("loki");
    
    private final List<Map<String, Object>> fakeValuesMaps;
    
    private final RandomService randomService;

    /**
     * <p>
     *     Resolves YAML file using the most specific path first based on language and country code.
     *      'en_US' would resolve in the following order:
     *      <ol>
     *          <li>/en-US.yml</li>
     *          <li>/en.yml</li>
     *      </ol>
     *      The search is case-insensitive, so the following will all resolve correctly.  Also, either a hyphen or
     *      an underscore can be used when constructing a instance.  This is legacy behavior and not
     *      condoned, but it will work.
     * @param randomService
     */
    public MockValuesService(TestEnvironment env, RandomService randomService) {
        if (env == null) {
            throw new IllegalArgumentException("Test environment is required");
        }
        this.randomService = randomService;
        final List<TestEnvironment> envs = new ArrayList<>();
        envs.add(new TestEnvironment(Environments.DEV));
        envs.add(new TestEnvironment(Environments.REF));
        envs.add(new TestEnvironment(Environments.PROD));
        final List<Map<String,Object>> all = new ArrayList<>(envs.size());
        for (final TestEnvironment t : envs) {
            final StringBuilder filename = new StringBuilder(t.getEnvironment().name().toLowerCase());
        
            final InputStream stream = findStream(filename.toString());
            if (stream != null) {
                all.add(fakerFromStream(stream, filename.toString()));
            }
        }

        this.fakeValuesMaps = Collections.unmodifiableList(all);
    }

    /**
     * @return the embedded faker: clause from the loaded Yml by the localeName, so .yml > en-us: > faker: 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected Map<String, Object> fakerFromStream(InputStream stream, String localeName) {
        final Map valuesMap = (Map) new Yaml().load(stream);
        final Map localeBased = (Map) valuesMap.get(localeName);
        return (Map) localeBased.get("loki");
    }

    private InputStream findStream(String filename) {
        InputStream streamOnClass = null;
        String filenameWithExtension = "resources/" + filename + ".yml";
        try {
            streamOnClass = getClass().getResourceAsStream(filenameWithExtension);
            if (streamOnClass == null) {
                streamOnClass = new FileInputStream(new File("src/main/com/warnermedia/data/loki/" + filenameWithExtension));
            }
            if (streamOnClass != null) {
                return streamOnClass;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return getClass().getClassLoader().getResourceAsStream(filenameWithExtension);
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object fetch(String key) {
		List<Object> valuesArray = (List<Object>) fetchObject(key);
        return valuesArray == null ? null : valuesArray.get(randomService.nextInt(valuesArray.size()));
    }

    /**
     * Same as {@link #fetch(String)} except this casts the result into a String.
     *
     * @param key
     * @return
     */
    public String fetchString(String key) {
        return (String) fetch(key);
    }

    /**
     * Safely fetches a key.
     * <p>
     * If the value is null, it will return an empty string.
     * <p>
     * If it is a list, it will assume it is a list of strings and select a random value from it.
     * <p>
     * If the retrieved value is an slash encoded regular expression such as {@code /[a-b]/} then
     * the regex will be converted to a regexify expression and returned (ex. {@code #regexify '[a-b]'})
     * <p>
     * Otherwise it will just return the value as a string.
     *
     * @param key           the key to fetch from the YML structure.
     * @param defaultIfNull the value to return if the fetched value is null
     * @return see above
     */
    @SuppressWarnings("unchecked")
    public String safeFetch(String key, String defaultIfNull) {
        Object o = fetchObject(key);
        String value = defaultIfNull;
        if (o == null) return defaultIfNull;
        if (o instanceof List) {
            List<String> values = (List<String>) o;
            value = values.get(randomService.nextInt(values.size()));
        } else {
        	value = (String) o;
        }
        return value;
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key key contains path to an object. Path segment is separated by
     *            dot. E.g. name.first_name
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object fetchObject(String key) {
        String[] path = key.split("\\.");

        Object result = null;
        for (Map<String, Object> fakeValuesMap : fakeValuesMaps) {
            Object currentValue = fakeValuesMap;
            for (int p = 0; currentValue != null && p < path.length; p++) {
                currentValue = ((Map<String, Object>) currentValue).get(path[p]);
            }
            result = currentValue;
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p/>
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(randomService.nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * Resolves a key to a method on an object.
     *
     * #{hello} with result in a method call to current.hello();
     *
     * #{Person.hello_someone} will result in a method call to person.helloSomeone();
     *
     */
    public String resolve(String key, Object current, Loki root) {
        final String expression = safeFetch(key, null);
        if (expression == null) {
            throw new RuntimeException(key + " resulted in null expression");
        }

        return resolveExpression(expression, current, root);
    }

    /**
     * <p>processes a expression in the style #{X.y} using the current objects as the 'current' location
     * within the yml file (or the object hierarchy as it were).
     * </p>
     * <p>
     *     #{Address.streetName} would get resolved to
     * </p>
     * <p>
     *     #{address.street} would get resolved to the YAML > locale: faker: address: street:
     * </p>
     * <p>
     *     Combinations are supported as well: "#{x} #{y}"
     * </p>
     * <p>
     *     Recursive templates are supported.  if "#{x}" resolves to "#{Address.streetName}" then "#{x}" resolves to
     *     .
     * </p>
     */
    protected String resolveExpression(String expression, Object current, Loki root) {
        final Matcher matcher = EXPRESSION_PATTERN.matcher(expression);

        String result = expression;
        while (matcher.find()) {
            final String escapedDirective = matcher.group(0);
            final String directive = matcher.group(1);
            List<String> args = new ArrayList<>();
            for (int i=2;i < matcher.groupCount()+1 && matcher.group(i) != null;i++) {
                args.add(matcher.group(i));
            }
            
            // resolve the expression and reprocess it to handle recursive templates
            String resolved = resolveExpression(directive, args, current, root);
            if (resolved == null) {
                throw new RuntimeException("Unable to resolve " + escapedDirective + " directive.");
            }

            resolved = resolveExpression(resolved, current, root);
            result = result.replace(escapedDirective, resolved);
        }
        return result;
    }

    /**
     * <h1>Search Order</h1>
     * <ul>
     *     <li>Search for methods on the current object</li>
     *     <li>local keys in Yaml File</li>
     *     <li>Search for methods on faker child objects</li>
     *     <li>Search for keys in yaml file by transforming object reference to yaml reference</li>
     * </ul>
     * @return null if unable to resolve
     */
    private String resolveExpression(String directive, List<String> args, Object current, Loki root) {
        // name.name (resolve locally)
        // Name.first_name (resolve to faker.name().firstName())
        final String simpleDirective = (isDotDirective(directive) || current == null) 
                ? directive 
                : classNameToYamlName(current) + "." + directive;
        
        String resolved = null;
        // resolve method references on CURRENT object like #{number_between '1','10'} on Number or
        // #{ssn_valid} on IdNumber
        if (!isDotDirective(directive)) {
            resolved = resolveFromMethodOn(current, directive, args);
        }

        // simple fetch of a value from the yaml file. the directive may have been mutated
        // such that if the current yml object is car: and directive is #{wheel} then 
        // car.wheel will be looked up in the YAML file.
        if (resolved == null) {
            resolved = safeFetch(simpleDirective, null);
        }
        
        return resolved;
    }

    private boolean isDotDirective(String directive) {
        return directive.contains(".");
    }

    /**
     * @return a yaml style name from the classname of the supplied object (PhoneNumber => phone_number) 
     */
    private String classNameToYamlName(Object current) {
        return javaNameToYamlName(current.getClass().getSimpleName());
    }

    /**
     * @return a yaml style name like 'phone_number' from a java style name like 'PhoneNumber' 
     */
    private String javaNameToYamlName(String expression) {
        return expression.replaceAll("([A-Z])", "_$1")
                .substring(1)
                .toLowerCase();
    }



    /**
     * Given a directive like 'firstName', attempts to resolve it to a method.  For example if obj is an instance of
     *then this method would return.  Returns null if the directive is nested
     * (i.e. has a '.') or the method doesn't exist on the <em>obj</em> object.
     */
    private String resolveFromMethodOn(Object obj, String directive, List<String> args) {
        try {
            final MethodAndCoercedArgs accessor = accessor(obj, directive, args);
            return (accessor == null)
                    ? null
                    : string(accessor.invoke(obj));
        } catch (Exception e) {
            log.log(Level.FINE, "Can't call " + directive + " on " + obj, e);
            return null;
        }
    }
    
    /**
     * Find an accessor by name ignoring case.
     */
    private MethodAndCoercedArgs accessor(Object onObject, String name, List<String> args) {
        log.log(Level.FINE, "Find accessor named " + name + " on " + onObject.getClass().getSimpleName() + " with args " + args);
        
        for (Method m : onObject.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase(name) 
                    && m.getParameterTypes().length == args.size()) {
                final List<Object> coercedArguments = coerceArguments(m, args);
                if (coercedArguments != null) {
                    return new MethodAndCoercedArgs(m, coercedArguments);
                }
            }
        }

        if (name.contains("_")) {
            return accessor(onObject, name.replaceAll("_", ""), args);
        }
        return null;
    }

    /**
     * Coerce arguments in <em>args</em> into the appropriate types (if possible) for the parameter arguments
     * to <em>accessor</em>.
     * @return array of coerced values if successful, null otherwise
     * @throws Exception if unable to coerce
     */
    private List<Object> coerceArguments(Method accessor, List<String> args) {
        final List<Object> coerced = new ArrayList<Object>();
        for (int i = 0; i < accessor.getParameterTypes().length; i++) {

            Class<?> toType = ClassUtils.primitiveToWrapper(accessor.getParameterTypes()[i]);
            try {
                final Constructor<?> ctor = toType.getConstructor(String.class);
                final Object coercedArgument = ctor.newInstance(args.get(i));

                coerced.add(coercedArgument);
            } catch (Exception e) {
                log.fine("Unable to coerce " + args.get(i) + " to " + toType.getSimpleName() + " via " + toType.getSimpleName() + "(String) constructor.");
                return null;
            }
        }
        return coerced;
    }
    
    private String string(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

    /**
     * simple wrapper class around an accessor and a list of coerced arguments.
     * this is useful as we get to find the method and coerce the arguments in one
     * shot, returning both when successful.  This saves us from doing it more than once (coercing args).
     */
    private class MethodAndCoercedArgs {

        private final Method method;
        
        private final List<Object> coerced;

        private MethodAndCoercedArgs(Method m, List<Object> coerced) {
            this.method = requireNonNull(m, "method cannot be null");
            this.coerced = requireNonNull(coerced, "coerced arguments cannot be null");
        }
        
        private Object invoke(Object on) throws InvocationTargetException, IllegalAccessException {
            return method.invoke(on, coerced.toArray());
        }

        /**
         * source level precludes me from using Objects.requireNonNull
         */
        private <T> T requireNonNull(T instance, String messageIfNull) {
            if (instance == null) {
                throw new NullPointerException(messageIfNull);
            }
            return instance;
        }
    }

}
