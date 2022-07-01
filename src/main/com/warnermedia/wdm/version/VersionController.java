package com.warnermedia.wdm.version;

import com.warnermedia.wdm.utils.WebDriverManagerException;

public class VersionController implements VersionGetter {

    private final VersionGetter vg;

    public VersionController(VersionGetter vg) {
        this.vg = vg;
    }

    @Override
    public void setChromeVersion() throws WebDriverManagerException {
        vg.setChromeVersion();
    }

    @Override
    public String getVersion() {
        return vg.getVersion();
    }
}
