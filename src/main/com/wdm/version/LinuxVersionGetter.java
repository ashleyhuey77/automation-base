package com.wdm.version;

import com.utils.ex.WebDriverManagerException;

public class LinuxVersionGetter extends AbstractVersionGetter implements VersionGetter {

    private String version;

    @Override
    public void setChromeVersion() throws WebDriverManagerException {
        try {
            version = executeCommand("bash", "-c", "google-chrome --version");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String getVersion() {
        return version;
    }
}
