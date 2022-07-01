package com.warnermedia.config.data;

import com.app.file.*;
import com.app.file.Error;
import com.utils.CredentialsType;

public class UserHelper {

    public static byte[] getName(CredentialsType type) {
        User user = getUser(type);
        return user.getName();
    }

    public static void setName(CredentialsType type, String un) {
        User user = getUser(type);
        user.setName(un.getBytes());
    }

    public static byte[] getPassword(CredentialsType type) {
        User user = getUser(type);
        return user.getPassword();
    }

    public static void setPassword(CredentialsType type, String pwd) {
        User user = getUser(type);
        user.setPassword(pwd.getBytes());
    }

    private static User getUser(CredentialsType type) {
        UserFactory factory = UserFactory.factory((builder) -> {
            builder.add(CredentialsType.BASE, Base::new);
            builder.add(CredentialsType.MIRA, Mira::new);
        });
        return factory.create(type);
    }
}
