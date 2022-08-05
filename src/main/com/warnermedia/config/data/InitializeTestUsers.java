package com.warnermedia.config.data;

import com.app.Decrypter;
import com.app.data.DataMapper;
import com.utils.CredentialsType;

public class InitializeTestUsers {

    public static void initialize() throws Exception {
        if ((UserHelper.getName(CredentialsType.BASE) == null)) {
            Decrypter.decryptFromDB(CredentialsType.BASE);
            UserHelper.setName(CredentialsType.BASE, DataMapper.getCredentials().name);
            UserHelper.setPassword(CredentialsType.BASE, DataMapper.getCredentials().password);
        }

        if ((UserHelper.getName(CredentialsType.MIRA) == null)) {
            Decrypter.decryptFromDB(CredentialsType.MIRA);
            UserHelper.setName(CredentialsType.MIRA, DataMapper.getCredentials().name);
            UserHelper.setPassword(CredentialsType.MIRA, DataMapper.getCredentials().password);
        }
    }
}
