package com.wdm.version;

import com.utils.ex.WebDriverManagerException;

public class MacVersionGetter extends AbstractVersionGetter implements VersionGetter {

    private String version;

    @Override
    public void setChromeVersion() throws WebDriverManagerException {
        try {
            version = executeCommand("bash", "-c", "/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome --version");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String getVersion() {
        return version;
    }
}
