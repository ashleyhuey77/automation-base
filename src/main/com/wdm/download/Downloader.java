package com.wdm.download;


import com.config.TestException;

public interface Downloader {

    void findUrl(String expectedVersion) throws TestException;

    void downloadFile() throws TestException;
}
