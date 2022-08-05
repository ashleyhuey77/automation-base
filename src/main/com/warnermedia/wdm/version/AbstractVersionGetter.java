package com.warnermedia.wdm.version;

import com.warnermedia.utils.ex.WebDriverManagerException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
                System.out.println(output);
            } else {
                //abnormal...
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
