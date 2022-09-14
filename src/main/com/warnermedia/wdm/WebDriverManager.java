package com.warnermedia.wdm;

import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.FileDownloadException;
import com.warnermedia.utils.ex.MismatchedVersionException;
import com.warnermedia.utils.retry.Retry;
import com.warnermedia.utils.retry.TestOperation;
import com.warnermedia.wdm.download.*;
import com.warnermedia.wdm.remove.LinuxRemover;
import com.warnermedia.wdm.remove.MacRemover;
import com.warnermedia.wdm.remove.RemoveController;
import com.warnermedia.wdm.remove.WindowsRemover;
import com.warnermedia.wdm.utils.Constants;
import com.warnermedia.wdm.utils.OS;
import com.warnermedia.utils.ex.WebDriverManagerException;
import com.warnermedia.wdm.version.*;

import java.io.File;

public class WebDriverManager {

    private static String version;
    private static OS os;
    private static TestOperation op;

    public WebDriverManager() {
        os = OS.getOperatingSystem();
    }

    public WebDriverManager chromedriver() throws WebDriverManagerException {
        try {
            File file = new File(Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME);
            if (!file.exists()) {
                VersionController vg = null;
                switch (os) {
                    case LINUX:
                        vg = new VersionController(new LinuxVersionGetter());
                        break;
                    case MAC:
                        vg = new VersionController(new MacVersionGetter());
                        break;
                    case WINDOWS:
                        vg = new VersionController(new WindowsVersionGetter());
                        break;
                    default:
                }
                vg.setChromeVersion();
                version = vg.getVersion();
            }
        } catch (Exception e) {
            throw new WebDriverManagerException("Something went wrong trying to get the current version of chrome on this machine.");
        }
        return this;
    }

    public WebDriverManager setup() throws Exception {
        try {
            TestOperation test = new Download(os, version,
                    new MismatchedVersionException(ErrorCode.DRIVER_VERSION),
                    new FileDownloadException(ErrorCode.FILE_DOWNLOAD)
            );
            final Retry retry = new Retry(
                    test,
                    3,  //3 attempts
                    800, //800 ms delay between attempts
                    e -> MismatchedVersionException.class.isAssignableFrom(e.getClass()),
                    f -> FileDownloadException.class.isAssignableFrom(f.getClass())
            );
            op = retry;
            op.perform();
        } catch (Exception e) {
            throw e;
        }
        return this;
    }

    public WebDriverManager teardown() throws Exception {
        try {
            File file = new File(Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME);
            if (file.exists()) {
                RemoveController fm = null;
                switch (os) {
                    case LINUX:
                        fm = new RemoveController(new LinuxRemover());
                        String[] paths = {Constants.ZIP_FILE_NAME, Constants.FILE_NAME};
                        fm.remove(Constants.DRIVER_FILE_PATH, paths);
                        break;
                    case MAC:
                        fm = new RemoveController(new MacRemover());
                        String[] mpaths = {Constants.ZIP_FILE_NAME, Constants.FILE_NAME};
                        fm.remove(Constants.DRIVER_FILE_PATH, mpaths);
                        break;
                    case WINDOWS:
                        fm = new RemoveController(new WindowsRemover());
                        String[] wpaths = {Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME, Constants.DRIVER_FILE_PATH + Constants.FILE_NAME};
                        fm.remove(Constants.DRIVER_FILE_PATH, wpaths);
                        break;
                    default:
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return this;
    }


}
