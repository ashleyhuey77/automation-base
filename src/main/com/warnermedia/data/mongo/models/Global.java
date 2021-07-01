package com.warnermedia.data.mongo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warnermedia.config.TestException;
import com.warnermedia.data.mongo.config.SlugPreface;
import com.warnermedia.page.utils.date.DatePicker;
import com.warnermedia.page.utils.date.PresentDate;
import com.warnermedia.utils.TestUtils;

import java.util.List;

public class Global {

    @JsonProperty("users")
    public List<String> users;

    @JsonProperty("recording_duration")
    public String recordingDuration;

    @JsonProperty("recording_start_time")
    public String recordingStartTime;

    @JsonProperty("messages")
    public List<String> messages;

    @JsonProperty("network")
    public String network;

    @JsonProperty("cost_center")
    public String costCenter;

    @JsonProperty("program_group")
    public String programGroup;

    @JsonProperty("rundown_mapping")
    public String rundownMapping;

    @JsonProperty("show_name")
    public String showName;

    @JsonProperty("asset_status")
    public String assetStatus;

    @JsonProperty("asset_status_fulfilled")
    public String assetStatusFulfilled;

    @JsonProperty("email")
    public String email;

    @JsonProperty("other_archive_info_blank")
    public String otherArchiveInfoBlank;

    @JsonProperty("other_archive_info_filled_out")
    public String otherArchiveInfoFilledOut;

    @JsonProperty("other_misc_info")
    public String otherMiscInfo;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("image_file_path")
    private String imageFilePath;

    @JsonProperty("destination")
    public String destination;

    @JsonProperty("auth_token")
    public String authToken;

    @JsonProperty("requests_auth_token")
    public String requestsAuthToken;

    @JsonProperty("stream_url")
    public String streamUrl;

    @JsonProperty("voice_file_path")
    private String voiceFilePath;


    public String filePath() {
        return TestUtils.getRelativePath() + filePath;
    }

    public String voiceFilePath() {
        return TestUtils.getRelativePath() + voiceFilePath;
    }

    public String imageFilePath() {
        return TestUtils.getRelativePath() + imageFilePath;
    }

    public String getSlugName(final SlugPreface preface) throws TestException {

        String name = null;
        switch (preface) {
            case Auto:
                name = SlugPreface.Auto.toString();
                break;
            case Sequence:
                name = SlugPreface.Sequence.toString();
                break;
            case Duplicate:
                name = SlugPreface.Duplicate.toString();
                break;
            case FileIngest:
                name = SlugPreface.FileIngest.toString();
                break;
            case MS2:
                name = SlugPreface.MS2.toString();
                break;
            case Temp:
                name = SlugPreface.Temp.toString();
                break;
            case testAsset:
                name = SlugPreface.testAsset.toString();
                break;
            case EALoad:
                name = SlugPreface.EALoad.toString();
                break;
            default:
                break;
        }
        final DatePicker picker = new PresentDate();
        final String currentDateTime = picker.getDate("MMddHHmmss");
        return name + "_" + currentDateTime;
    }
}
