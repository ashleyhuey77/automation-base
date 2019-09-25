package common.utils.creds;

import java.util.function.Supplier;
import com.app.creds.CredentialsType;

public interface Builder {
	void add(CredentialsType type, Supplier<User> supplier);
}
