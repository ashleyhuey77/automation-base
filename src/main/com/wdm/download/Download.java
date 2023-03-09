package com.wdm.download;

import com.config.TestException;
import com.utils.retry.TestOperation;
import com.wdm.utils.Constants;
import com.wdm.utils.OS;

import java.io.File;
import java.util.*;

public class Download implements TestOperation<String> {
    private final OS os;
    private final String version;
    private final Deque<TestException> errors;

    public Download(OS os, String version, TestException... errors) {
        this.os = os;
        this.version = version;
        this.errors = new ArrayDeque<>(Collections.unmodifiableList(Arrays.asList(errors)));
    }

    @Override
    public String perform() throws TestException {
        if (!this.errors.isEmpty()) {
            throw this.errors.pop();
        }
        try {
            File file = new File(Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME);
            if (!file.exists()) {
                DownloadController d = null;
                switch (this.os) {
                    case LINUX:
                        d = new DownloadController(new LinuxDownloader());
                        break;
                    case MAC:
                        d = new DownloadController(new MacDownloader());
                        break;
                    case WINDOWS:
                        d = new DownloadController(new WindowsDownloader());
                        break;
                    default:
                }
                d.findUrl(version);
                d.downloadFile();
            }
        } catch (TestException e) {
            throw e;
        }
        return "SUCCESS";
    }
}
