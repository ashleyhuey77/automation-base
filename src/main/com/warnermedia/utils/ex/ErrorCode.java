package com.warnermedia.utils.ex;

public enum ErrorCode {

    CHROMEDRIVER(0, "There was an issue while launching the chromedriver."),
    DRIVER_VERSION(1, "Unable to find a matching driver version in the google chromedriver download archive."),
    FILE_DOWNLOAD(2, "Something went wrong when trying to download the file from the url."),
    UNZIP(3, "Something went wrong when unzipping the downloaded file."),
    CLICK(4, "Unable to click on the element."),
    FIND_ELEMENT(5, "Unable to locate element."),
    WAIT(6, "The wait method timed out.");

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
