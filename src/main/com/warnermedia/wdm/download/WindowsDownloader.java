package com.warnermedia.wdm.download;

import com.warnermedia.wdm.utils.Constants;

public class WindowsDownloader extends AbstractDownloader implements Downloader {

    @Override
    public void findUrl(String expectedVersion) throws Exception {
        try {
            findURL(expectedVersion, "/chromedriver_win32.zip");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void downloadFile() throws Exception {
        try {
            String fullPath = Constants.DRIVER_FILE_PATH + Constants.ZIP_FILE_NAME;
            downloadFile(Constants.DRIVER_FILE_PATH, Constants.ZIP_FILE_NAME);
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command("cmd.exe", "/c", "Expand-Archive -Path " + fullPath + "-DestinationPath " + Constants.DRIVER_FILE_PATH);
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Unzip command executed successfully.");
            } else {
                //abnormal...
            }
            process.destroy();
        } catch (Exception e) {
            throw e;
        }
    }
}
