package com.warnermedia.config.data;

import com.utils.CredentialsType;

import java.util.function.Supplier;

public interface UserBuilder {
    void add(CredentialsType name, Supplier<User> supplier);
}
