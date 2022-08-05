package com.warnermedia.wdm.version;

import com.warnermedia.utils.ex.WebDriverManagerException;

public interface VersionGetter {

    void setChromeVersion() throws WebDriverManagerException;

    String getVersion();
}
