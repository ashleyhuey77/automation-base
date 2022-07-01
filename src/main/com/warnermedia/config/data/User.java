package com.warnermedia.config.data;

public interface User {

    void setName(byte[] value);

    byte[] getName();

    void setPassword(byte[] value);

    byte[] getPassword();
}
