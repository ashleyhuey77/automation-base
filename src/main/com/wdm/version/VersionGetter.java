package com.wdm.version;

import com.utils.ex.WebDriverManagerException;

public interface VersionGetter {

    void setChromeVersion() throws WebDriverManagerException;

    String getVersion();
}
