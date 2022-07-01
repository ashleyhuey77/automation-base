package com.warnermedia.wdm.version;

import com.warnermedia.wdm.utils.WebDriverManagerException;

public interface VersionGetter {

    void setChromeVersion() throws WebDriverManagerException;

    String getVersion();
}
