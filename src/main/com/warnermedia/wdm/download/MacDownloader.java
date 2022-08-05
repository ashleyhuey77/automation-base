package com.warnermedia.wdm.download;

import com.warnermedia.config.TestException;
import com.warnermedia.wdm.utils.Constants;

public class MacDownloader extends AbstractDownloader implements Downloader {

    public MacDownloader() {

    }

    @Override
    public void findUrl(String expectedVersion) throws TestException {
        try {
            findURL(expectedVersion, "/chromedriver_mac64.zip");
        } catch (TestException e) {
            throw e;
        }
    }

    @Override
    public void downloadFile() throws TestException {
        try {
            String[] c = {"bash", "-c", "unzip "};
            downloadFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME);
            unzipFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME, c);
        } catch (TestException e) {
            throw e;
        }
    }
}
