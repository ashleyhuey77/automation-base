package com.warnermedia.wdm.download;


import com.warnermedia.config.TestException;

public interface Downloader {

    void findUrl(String expectedVersion) throws TestException;

    void downloadFile() throws TestException;
}
