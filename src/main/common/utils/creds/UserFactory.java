package common.utils.creds;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.app.creds.CredentialsType;

public interface UserFactory {

	User create(CredentialsType type);
	
	static UserFactory factory(Consumer<Builder> consumer) {
		Map<CredentialsType, Supplier<User>> map = new HashMap<>();
		consumer.accept(map::put);
		return name -> map.get(name).get();
	}
}
