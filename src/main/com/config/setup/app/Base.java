package com.config.setup.app;

public class Base implements User {

    private static byte[] name;
    private static byte[] pwd;

    @Override
    public void setName(byte[] value) {
        Base.name = value;
    }

    @Override
    public byte[] getName() {
        return Base.name;
    }

    @Override
    public void setPassword(byte[] value) {
        Base.pwd = value;
    }

    @Override
    public byte[] getPassword() {
        return Base.pwd;
    }
}
