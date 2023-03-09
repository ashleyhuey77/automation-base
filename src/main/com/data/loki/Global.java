package com.data.loki;

import com.config.TestException;
import com.data.config.SlugPreface;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.page.utils.date.DatePicker;
import com.page.utils.date.PresentDate;
import com.utils.TestUtils;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonProperty("mkv_file_path")
    private String mkvFilePath;

    @JsonProperty("avi_file_path")
    public String aviFilePath;

    @JsonProperty("ts_file_path")
    public String tsFilePath;

    @JsonProperty("destination")
    public String destination;

    @JsonProperty("auth_token")
    public String authToken;

    @JsonProperty("requests_auth_token")
    public String requestsAuthToken;

    @JsonProperty("active_grid_essence")
    public List<String> activeGridEssence;

    @JsonProperty("alerts_options")
    public List<String> alertsOptions;

    @JsonProperty("archive_explanation_options")
    public List<String> archiveExplanationOptions;

    @JsonProperty("archived_options")
    public List<String> archivedOptions;

    @JsonProperty("archive_recommendation_options")
    public List<String> archiveRecommendationOptions;

    @JsonProperty("archive_status_options")
    public List<String> archiveStatusOptions;

    @JsonProperty("asset_status_options")
    public List<String> assetStatusOptions;

    @JsonProperty("asset_status_ms_options")
    public List<String> assetStatusMsOptions;

    @JsonProperty("attached_to_edit_options")
    public List<String> attachedToEditOptions;

    @JsonProperty("boolean_options")
    public List<String> booleanOptions;

    @JsonProperty("category_options")
    public List<String> categoryOptions;

    @JsonProperty("city_text")
    public List<String> cityText;

    @JsonProperty("closed_captions_text")
    public List<String> closedCaptionsText;

    @JsonProperty("cnn_id_text")
    public List<String> cnnIdText;

    @JsonProperty("county_text")
    public List<String> countyText;

    @JsonProperty("country_text")
    public List<String> countryText;

    @JsonProperty("created_by_text")
    public List<String> createdByText;

    @JsonProperty("cut_from_text")
    public List<String> cutFromText;

    @JsonProperty("date_picker_option")
    public String datePickerOption;

    @JsonProperty("division_options")
    public List<String> divisionOptions;

    @JsonProperty("duplicate_from_options")
    public List<String> duplicateFromOptions;

    @JsonProperty("duration_text")
    public List<String> durationText;

    @JsonProperty("duration_text_short")
    public List<String> durationTextShort;

    @JsonProperty("duration_text_half_hour")
    public List<String> durationTextHalfHour;

    @JsonProperty("edit_status_options")
    public List<String> editStatusOptions;

    @JsonProperty("editor_options")
    public List<String> editorOptions;

    @JsonProperty("embargo_options")
    public List<String> embargoOptions;

    @JsonProperty("feed_requested_by_options")
    public List<String> feedRequestedByOptions;

    @JsonProperty("fonted_options")
    public List<String> fontedOptions;

    @JsonProperty("fonts_text")
    public List<String> fontsText;

    @JsonProperty("fonts_approved_options")
    public List<String> fontsApprovedOptions;

    @JsonProperty("fonts_approved_by_values")
    public List<String> fontsApprovedByValues;

    @JsonProperty("fonts_created_by_values")
    public List<String> fontsCreatedByValues;

    @JsonProperty("fonts_revised_by_values")
    public List<String> fontsRevisedByValues;

    @JsonProperty("for_program_options")
    public List<String> forProgramOptions;

    @JsonProperty("fulfilled_options")
    public List<String> fulfilledOptions;

    @JsonProperty("fulfilled_by_values")
    public List<String> fulfilledByValues;

    @JsonProperty("fulfilled_from_options")
    public List<String> fulfilledFromOptions;

    @JsonProperty("held_options")
    public List<String> heldOptions;

    @JsonProperty("hold_requested_by_values")
    public List<String> holdRequestedByOptions;

    @JsonProperty("in_showmgr_options")
    public List<String> inShowMgrOptions;

    @JsonProperty("ingest_system_options")
    public List<String> ingestSystemOptions;

    @JsonProperty("edit_system_options")
    public List<String> editSystemOptions;

    @JsonProperty("logged_options")
    public List<String> loggedOptions;

    @JsonProperty("metadata_killdate_options")
    public List<String> metadataKilldateOptions;

    @JsonProperty("ms_origin_options")
    public List<String> msOriginOptions;

    @JsonProperty("music_info_options")
    public List<String> musicInfoOptions;

    @JsonProperty("other_location_options")
    public List<String> otherLocationOptions;

    @JsonProperty("outcue_options")
    public List<String> outcueOptions;

    @JsonProperty("photojournalist_options")
    public List<String> photojournalistOptions;

    @JsonProperty("producer_options")
    public List<String> producerOptions;

    @JsonProperty("on_playback_options")
    public List<String> onPlaybackOptions;

    @JsonProperty("native_aspect_ratio_options")
    public List<String> nativeAspectRatioOptions;

    @JsonProperty("network_options")
    public List<String> networkOptions;

    @JsonProperty("playback_held_options")
    public List<String> playbackHeldOptions;

    @JsonProperty("production_held_options")
    public List<String> productionHeldOptions;

    @JsonProperty("production_type_options")
    public List<String> productionTypeOptions;

    @JsonProperty("purged_assets_options")
    public List<String> purgedAssetsOptions;

    @JsonProperty("restrictions_text")
    public List<String> restrictionsText;

    @JsonProperty("reporter_text")
    public List<String> reporterText;

    @JsonProperty("revised_by_values")
    public List<String> revisedByValues;

    @JsonProperty("router_text")
    public List<String> routerText;

    @JsonProperty("slug_text")
    public List<String> slugText;

    @JsonProperty("source_text")
    public List<String> sourceText;

    @JsonProperty("source_approved_options")
    public List<String> sourceApprovedOptions;

    @JsonProperty("source_approved_by_text")
    public List<String> sourceApprovedByText;

    @JsonProperty("source_created_by_text")
    public List<String> sourceCreatedByText;

    @JsonProperty("source_revised_by_text")
    public List<String> sourceRevisedByText;

    @JsonProperty("source_status_options")
    public List<String> sourceStatusOptions;

    @JsonProperty("speaker_options")
    public List<String> speakerOptions;

    @JsonProperty("state_options")
    public List<String> stateOptions;

    @JsonProperty("status_options")
    public List<String> statusOptions;

    @JsonProperty("stills_options")
    public List<String> stillsOptions;

    @JsonProperty("stills_created_by_values")
    public List<String> stillsCreatedByValues;

    @JsonProperty("technical_notes_options")
    public List<String> technicalNotesOptions;

    @JsonProperty("template_asset_options")
    public List<String> templateAssetOptions;

    @JsonProperty("transferred_by_values")
    public List<String> transferredByValues;

    @JsonProperty("type_options")
    public List<String> typeOptions;

    @JsonProperty("usage_text")
    public List<String> usageText;

    @JsonProperty("wires_options")
    public List<String> wiresOptions;

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

    public String mkvFilePath() {
        return TestUtils.getRelativePath() + mkvFilePath;
    }
    public String tsFilePath() {
        return TestUtils.getRelativePath() + tsFilePath;
    }
    public String aviFilePath() {
        return TestUtils.getRelativePath() + aviFilePath;
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
