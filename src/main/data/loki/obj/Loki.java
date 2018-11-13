package data.loki.obj;

import java.util.Random;
import data.loki.service.MockValuesService;
import data.loki.service.RandomService;
import data.loki.service.TestEnvironment;

public class Loki {
	
	private final RandomService randomService;
    private final MockValuesService mockValuesService;
    
    private final Person person;
    private final Slug slug;
    private final Video video;
    private final WorkOrder workOrder;
    
    public Loki(TestEnvironment env, Random random) {
    	this.randomService = new RandomService(random);
    	this.mockValuesService = new MockValuesService(env, randomService);
    	
    	this.person = new Person(this);
    	this.slug = new Slug(this);
    	this.video = new Video(this);
    	this.workOrder = new WorkOrder(this);
    }
    
    public Person person() {
    	return person;
    }
    
    public Slug slug() {
    	return slug;
    }
    
    public Video video() {
    	return video;
    }
    
    public WorkOrder workOrder() {
    	return workOrder;
    }
    
    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p>
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        return mockValuesService.numerify(numberString);
    }
    
    MockValuesService mockValuesService() {
        return this.mockValuesService;
    }

}
