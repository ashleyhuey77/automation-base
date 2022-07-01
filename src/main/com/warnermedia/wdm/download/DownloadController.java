package com.warnermedia.wdm.download;

public class DownloadController implements Downloader {

    private final Downloader downloader;

    public DownloadController(Downloader downloader) {
        this.downloader = downloader;
    }
    @Override
    public void findUrl(String expectedVersion) throws Exception {
        downloader.findUrl(expectedVersion);
    }

    @Override
    public void downloadFile() throws Exception {
        downloader.downloadFile();
    }
}
