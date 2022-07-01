package com.warnermedia.wdm.version;

import com.warnermedia.wdm.utils.WebDriverManagerException;

public class WindowsVersionGetter extends AbstractVersionGetter implements VersionGetter {

    private String version;

    @Override
    public void setChromeVersion() throws WebDriverManagerException {
        try {
            version = executeCommand("cmd.exe", "/c", "wmic datafile where name=\"C:\\\\Program Files (x86)\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe\" get Version /value");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String getVersion() {
        return version;
    }
}
