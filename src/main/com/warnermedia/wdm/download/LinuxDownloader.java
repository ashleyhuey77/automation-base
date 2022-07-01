package com.warnermedia.wdm.download;

import com.warnermedia.wdm.utils.Constants;

public class LinuxDownloader extends AbstractDownloader implements Downloader {

    @Override
    public void findUrl(String expectedVersion) throws Exception {
        try {
            findURL(expectedVersion, "/chromedriver_linux64.zip");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void downloadFile() throws Exception {
        try {
            String[] c = {"bash", "-c", "unzip "};
            downloadFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME);
            unzipFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME, c);
        } catch (Exception e) {
            throw e;
        }
    }
}
