package com.warnermedia.wdm.download;

import com.warnermedia.config.TestException;

public class DownloadController implements Downloader {

    private final Downloader downloader;

    public DownloadController(Downloader downloader) {
        this.downloader = downloader;
    }
    @Override
    public void findUrl(String expectedVersion) throws TestException {
        downloader.findUrl(expectedVersion);
    }

    @Override
    public void downloadFile() throws TestException {
        downloader.downloadFile();
    }
}
