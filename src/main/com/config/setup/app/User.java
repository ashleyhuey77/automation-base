package com.config.setup.app;

public interface User {

    void setName(byte[] value);

    byte[] getName();

    void setPassword(byte[] value);

    byte[] getPassword();
}
