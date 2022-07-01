package com.warnermedia.wdm.remove;

import com.warnermedia.wdm.utils.WebDriverManagerException;

import java.io.File;

public abstract class AbstractRemover {

    public AbstractRemover() {

    }

    protected void removeAFile(String filePath, String fileName, String[] command) throws Exception {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.directory(new File(filePath));
            processBuilder.command(command[0], command[1], command[2] + fileName);
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Unzip command executed successfully.");
            } else {
                throw new WebDriverManagerException("Exit code is not as expected.");
            }
            process.destroy();
        } catch (Exception e) {
            throw e;
        }
    }
}
