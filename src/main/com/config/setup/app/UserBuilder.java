package com.config.setup.app;

import com.utils.CredentialsType;

import java.util.function.Supplier;

public interface UserBuilder {
    void add(CredentialsType name, Supplier<User> supplier);
}
