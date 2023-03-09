package com.wdm.remove;

import com.utils.ConsoleDecoration;
import com.utils.ex.WebDriverManagerException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
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
                log.info("{}{}Unzip command executed successfully.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
            } else {
                throw new WebDriverManagerException("Exit code is not as expected.");
            }
            process.destroy();
        } catch (Exception e) {
            throw e;
        }
    }
}
