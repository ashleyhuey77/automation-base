package com.warnermedia.wdm.download;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.warnermedia.config.TestException;
import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.FileDownloadException;
import com.warnermedia.utils.ex.MismatchedVersionException;
import com.warnermedia.wdm.models.ListBucketResult;
import com.warnermedia.utils.ex.WebDriverManagerException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractDownloader {

    private String endpoint;
    private HttpURLConnection connection;
    private URL url;

    public AbstractDownloader() {

    }

    public void findURL(String expectedVersion, String fileName) throws TestException {
        try {
            try {
                url = new URL("https://chromedriver.storage.googleapis.com/");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/xml");

                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    throw new FileDownloadException("Something is wrong with " + url.toString() + ". The response returned a " + responseCode, ErrorCode.FILE_DOWNLOAD);
                }
            } catch (Exception e) {
                throw new FileDownloadException("Something went wrong when trying to connect to the url " + url.toString(), ErrorCode.FILE_DOWNLOAD);
            }
            try {
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
                } else {
                    throw new MismatchedVersionException("The version needed was " + expectedVersion, ErrorCode.DRIVER_VERSION);
                }
                input.close();
            } catch (Exception e) {
                throw new FileDownloadException("Something went wrong when trying to connect to the url " + url.toString(), ErrorCode.FILE_DOWNLOAD);
            }

            connection.disconnect();
        } catch (TestException e) {
            throw e;
        }
    }

    public void downloadFile(String outputPath, String fileName) throws TestException
    {
        try {
            File directory = new File(outputPath);
            if (!directory.exists()){
                directory.mkdir();
            }
            URL url = new URL(endpoint);
            try (InputStream in = url.openStream();
                 ReadableByteChannel rbc = Channels.newChannel(in);
                 FileOutputStream fos = new FileOutputStream(outputPath + fileName)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (Exception e) {
                throw new FileDownloadException("Make sure output path " + outputPath + fileName + " and endpoint " + endpoint + " are correct and working paths.", ErrorCode.FILE_DOWNLOAD);
            }
        } catch (FileDownloadException e) {
            throw e;
        } catch (Exception e) {
            throw new FileDownloadException("Something went wrong when trying to connect to the url " + url.toString(), ErrorCode.FILE_DOWNLOAD);
        }
    }

    public void unzipFile(String filePath, String fileName, String[] commands) throws TestException {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.directory(new File(filePath));
            processBuilder.command(commands[0], commands[1], commands[2] + fileName);
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Unzip command executed successfully.");
            } else {
                process.destroy();
                throw new FileDownloadException("Make sure file path " + filePath + " is correct and accessible.", ErrorCode.UNZIP);
            }
            process.destroy();
        } catch (Exception e) {
            throw new FileDownloadException("Make sure file path " + filePath + " is correct and accessible.", ErrorCode.UNZIP);
        }
    }
}
