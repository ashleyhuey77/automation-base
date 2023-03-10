package com.wdm.version;

import com.utils.ex.ErrorCode;
import com.utils.ex.VersionFormatException;
import com.utils.ex.WebDriverManagerException;
import com.utils.ConsoleDecoration;
import com.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public abstract class AbstractVersionGetter {

    public AbstractVersionGetter() {

    }

    protected String executeCommand(String p1, String p2, String command) throws WebDriverManagerException {
        String result = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command(p1, p2, command);
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            result = output.toString().replace("Google Chrome ", "");
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                log.info("{}{}{}{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, result, ConsoleDecoration.RESET.value);
            } else {
                //abnormal...
            }
            if (TestUtils.isNullOrBlank(result)) {
                throw new VersionFormatException("Please download the latest version of Google Chrome to this machine.", ErrorCode.GOOGLE_CHROME);
            }
            String[] split = result.split("\\.");
            result = split[0] + "." + split[1] + "." + split[2];
            process.destroy();
        } catch (Exception e) {
            throw new WebDriverManagerException(e.getMessage());
        }
        return result;
    }
}
