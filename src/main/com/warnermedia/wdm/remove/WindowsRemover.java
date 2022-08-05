package com.warnermedia.wdm.remove;

import com.warnermedia.utils.ex.WebDriverManagerException;

public class WindowsRemover extends AbstractRemover implements Remover {
    @Override
    public void remove(String filePath, String[] fileName) throws Exception {
        try {
            for (String file : fileName) {
                ProcessBuilder processBuilder = new ProcessBuilder();

                processBuilder.command("cmd.exe", "/c", "del " + fileName);
                Process process = processBuilder.start();
                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("File removed successfully.");
                } else {
                    throw new WebDriverManagerException("Exit code is not as expected.");
                }

                process.destroy();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
