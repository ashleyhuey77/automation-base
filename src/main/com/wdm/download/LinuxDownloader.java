package com.wdm.download;

import com.config.TestException;
import com.wdm.utils.Constants;

public class LinuxDownloader extends AbstractDownloader implements Downloader {

    @Override
    public void findUrl(String expectedVersion) throws TestException {
        try {
            findURL(expectedVersion, "/chromedriver_linux64.zip");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void downloadFile() throws TestException {
        try {
            String[] c = {"bash", "-c", "unzip "};
            downloadFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME);
            unzipFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME, c);
        } catch (Exception e) {
            throw e;
        }
    }
}
