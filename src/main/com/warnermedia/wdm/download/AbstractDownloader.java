package com.warnermedia.wdm.download;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.warnermedia.wdm.models.ListBucketResult;
import com.warnermedia.wdm.utils.WebDriverManagerException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractDownloader {

    private String endpoint;

    public AbstractDownloader() {

    }

    public void findURL(String expectedVersion, String fileName) throws WebDriverManagerException {
        try {
            URL url = new URL("https://chromedriver.storage.googleapis.com/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/xml");

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new WebDriverManagerException("The chromedriver api response returned a " + responseCode);
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            XmlMapper xmlMapper = new XmlMapper();
            ListBucketResult lbr = xmlMapper.readValue(input, ListBucketResult.class);

            long count = lbr.getContents().stream().filter(e -> e.getKey().toLowerCase().contains(expectedVersion)).filter(e -> !e.getKey().contains("LATEST_RELEASE")).count();
            if (count > 0) {
                ListBucketResult.Contents key = lbr.getContents().stream().filter(e -> e.getKey().toLowerCase().contains(expectedVersion)).filter(e -> !e.getKey().contains("LATEST_RELEASE")).skip(count - 1).findFirst().orElse(null);
                if (key == null) {
                    key = lbr.getContents().stream().filter(e -> e.getKey().toLowerCase().contains(expectedVersion)).filter(e -> !e.getKey().contains("LATEST_RELEASE")).skip(count - 1).findFirst().orElse(null);
                }
                endpoint = "https://chromedriver.storage.googleapis.com/" + key.getKey().split("/")[0] + fileName;
            }
            input.close();

            connection.disconnect();
        } catch (Exception e) {
            throw new WebDriverManagerException("Unable to find a matching url. " + e.getMessage());
        }
    }

    public void downloadFile(String outputPath, String fileName) throws Exception
    {
        URL url = new URL(endpoint);
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputPath + fileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            throw e;
        }
    }

    public void unzipFile(String filePath, String fileName, String[] commands) throws IOException, InterruptedException {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.directory(new File(filePath));
            processBuilder.command(commands[0], commands[1], commands[2] + fileName);
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
