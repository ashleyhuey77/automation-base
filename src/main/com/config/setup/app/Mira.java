package com.config.setup.app;

public class Mira implements User {

    private static byte[] name;
    private static byte[] pwd;

    @Override
    public void setName(byte[] value) {
        Mira.name = value;
    }

    @Override
    public byte[] getName() {
        return Mira.name;
    }

    @Override
    public void setPassword(byte[] value) {
        Mira.pwd = value;
    }

    @Override
    public byte[] getPassword() {
        return Mira.pwd;
    }
}
