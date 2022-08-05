package com.warnermedia.wdm.version;

import com.warnermedia.utils.ex.WebDriverManagerException;

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
