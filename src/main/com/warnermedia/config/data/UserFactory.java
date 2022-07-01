package com.warnermedia.config.data;

import com.utils.CredentialsType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface UserFactory {
    User create(CredentialsType type);

    static UserFactory factory(Consumer<UserBuilder> consumer) {
        Map<CredentialsType, Supplier<User>> map = new HashMap<>();
        consumer.accept(map::put);
        return type -> map.get(type).get();
    }

}
