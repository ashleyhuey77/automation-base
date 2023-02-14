package com.warnermedia.wdm.download;

import com.warnermedia.config.TestException;
import com.warnermedia.utils.ConsoleDecoration;
import com.warnermedia.wdm.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WindowsDownloader extends AbstractDownloader implements Downloader {

    @Override
    public void findUrl(String expectedVersion) throws TestException {
        try {
            findURL(expectedVersion, "/chromedriver_win32.zip");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void downloadFile() throws TestException {
        try {
            String fullPath = Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME;
            downloadFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME);
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command("cmd.exe", "/c", "Expand-Archive -Path " + fullPath + "-DestinationPath " + Constants.DRIVER_FILE_PATH);
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                log.info("{}{}Unzip command executed successfully.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
            } else {
                //abnormal...
            }
            process.destroy();
        } catch (TestException e) {
            throw e;
        } catch (Exception e) {
            throw new TestException("An error occurred when trying to execute the windows command process.");
        }
    }
}
