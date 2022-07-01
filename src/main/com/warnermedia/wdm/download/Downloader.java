package com.warnermedia.wdm.download;

public interface Downloader {

    void findUrl(String expectedVersion) throws Exception;

    void downloadFile() throws Exception;
}
