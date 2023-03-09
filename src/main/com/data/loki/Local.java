package com.data.loki;

import com.data.config.DataMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Local {

    private static final String ATLANTA = "atlanta";
    private static final String ATLANTA_EVERTZ = "atlanta - evertz";
    private static final String LOS_ANGELES = "los angeles";
    private static final String NEW_YORK = "new york";
    private static final String SM1 = "sm1";

    @JsonProperty("encoders")
    private BureauList encoders;

    @JsonProperty("archived_assets")
    private BureauList archivedAssets;

    @JsonProperty("native_assets")
    private BureauList nativeAssets;

    @JsonProperty("routers")
    public BureauList routers;

    @JsonProperty("fs_base_urls")
    private Bureau fsBaseUrls;

    @JsonProperty("ms_base_urls")
    private Bureau msBaseUrls;

    @JsonProperty("ms_base_urls_new")
    private Bureau msBaseUrlsNew;

    @JsonProperty("midas_location")
    public String midasLocation;

    @JsonProperty("archive_cleaner_url")
    public String archiveCleanerUrl;

    @JsonProperty("sftp_endpoint")
    public String sftpEndpoint;

    @JsonProperty("midas_mc_location")
    public String midasMCLocation;

    @JsonProperty("ea_location")
    public String eaLocation;

    @JsonProperty("transfer_section")
    public String transferSection;

    @JsonProperty("transfer_option")
    public String transferOption;

    @JsonProperty("from_date_range")
    public String fromDateRange;

    @JsonProperty("to_date_range")
    public String toDateRange;

    @JsonProperty("fulfill_asset")
    public String fulfillAsset;

    @JsonProperty("duplicate_publish_asset")
    private Bureau duplicatePublishAsset;


    @JsonProperty("duplicate_publish_asset_load")
    private BureauList duplicatePublishAssetList;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class BureauList {

        @JsonProperty(ATLANTA)
        private List<String> atlanta;

        @JsonProperty(ATLANTA_EVERTZ)
        private List<String> atlantaEvertz;

        @JsonProperty(LOS_ANGELES)
        private List<String> losAngeles;

        @JsonProperty("ny")
        private List<String> ny;

        @JsonProperty(SM1)
        private List<String> sm1;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Bureau {

        @JsonProperty("atlanta")
        private String atlanta;

        @JsonProperty("atlanta - evertz")
        private String atlantaEvertz;

        @JsonProperty("los angeles")
        private String losAngeles;

        @JsonProperty("ny")
        private String ny;

        @JsonProperty("sm1")
        private String sm1;
    }

    public String encoder(final int indexToPickFromList) {
        String encoder = null;
        encoder = getEncoder(DataMapper.local().midasLocation.toLowerCase(), indexToPickFromList);
        return encoder;
    }

    public String multicityEncoder(final int indexToPickFromList) {
        String encoder = null;
        encoder = getEncoder(DataMapper.local().midasMCLocation.toLowerCase(), indexToPickFromList);
        return encoder;
    }

    private String getEncoder(final String location, final int indexToPickFromList) {
        String encoder = null;
        switch(location.toLowerCase()) {
            case LOS_ANGELES:
                final List<String> laencoders = encoders.losAngeles;
                encoder = getValueFromArray(laencoders, indexToPickFromList);
                break;
            case NEW_YORK:
                final List<String> nyencoders = encoders.ny;
                encoder = getValueFromArray(nyencoders, indexToPickFromList);
                break;
            case ATLANTA_EVERTZ:
                final List<String> atlEencoders = encoders.atlantaEvertz;
                encoder = getValueFromArray(atlEencoders, indexToPickFromList);
                break;
            case ATLANTA:
                final List<String> atlencoders = encoders.atlanta;
                encoder = getValueFromArray(atlencoders, indexToPickFromList);
                break;
            case SM1:
                final List<String> sm1encoders = encoders.sm1;
                encoder = getValueFromArray(sm1encoders, indexToPickFromList);
                break;
            default:
                encoder = "";
                break;

        }
        return encoder;
    }

    public List<String> dupePubAssetLoad() {
        List<String> enc = null;

        switch(DataMapper.local().eaLocation.toLowerCase()) {
            case LOS_ANGELES:
                enc = duplicatePublishAssetList.losAngeles;
                break;
            case NEW_YORK:
                enc = duplicatePublishAssetList.ny;
                break;
            case ATLANTA_EVERTZ:
                enc = duplicatePublishAssetList.atlantaEvertz;
                break;
            case ATLANTA:
                enc = duplicatePublishAssetList.atlanta;
                break;
            case SM1:
                enc = duplicatePublishAssetList.sm1;
                break;
            default:
                break;

        }
        return enc;
    }

    public String dupePubAsset() {
        String enc = null;

        switch(DataMapper.local().eaLocation.toLowerCase()) {
            case LOS_ANGELES:
                enc = duplicatePublishAsset.losAngeles;
                break;
            case NEW_YORK:
                enc = duplicatePublishAsset.ny;
                break;
            case ATLANTA_EVERTZ:
                enc = duplicatePublishAsset.atlantaEvertz;
                break;
            case ATLANTA:
                enc = duplicatePublishAsset.atlanta;
                break;
            case SM1:
                enc = duplicatePublishAsset.sm1;
                break;
            default:
                break;

        }
        return enc;
    }

    public List<String> encoderList() {
        List<String> enc = null;

        switch(DataMapper.local().midasLocation.toLowerCase()) {
            case LOS_ANGELES:
                enc = encoders.losAngeles;
                break;
            case NEW_YORK:
                enc = encoders.ny;
                break;
            case ATLANTA_EVERTZ:
                enc = encoders.atlantaEvertz;
                break;
            case ATLANTA:
                enc = encoders.atlanta;
                break;
            case SM1:
                enc = encoders.sm1;
                break;
            default:
                break;

        }
        return enc;
    }

    public String router(final int indexToPickFromList) {
        String router = null;
        router = getRouter(DataMapper.local().midasLocation.toLowerCase(), indexToPickFromList);
        return router;
    }

    public String multicityRouter(final int indexToPickFromList) {
        String router = null;
        router = getRouter(DataMapper.local().midasMCLocation.toLowerCase(), indexToPickFromList);
        return router;
    }

    public String archivedAsset(final int indexToPickFromList) {
        String router = null;
        router = getArchivedAsset(DataMapper.local().eaLocation.toLowerCase(), indexToPickFromList);
        return router;
    }

    public String nativeAsset(final int indexToPickFromList) {
        String router = null;
        router = getNativeAsset(DataMapper.local().eaLocation.toLowerCase(), indexToPickFromList);
        return router;
    }

    private String getNativeAsset(final String location, final int indexToPickFromList) {
        String native_asset = null;

        switch(location) {
            case NEW_YORK:
                final List<String> nyAA = nativeAssets.ny;
                native_asset = getValueFromArray(nyAA, indexToPickFromList);
                break;
            case ATLANTA:
                final List<String> atlAA = nativeAssets.atlanta;
                native_asset = getValueFromArray(atlAA, indexToPickFromList);
                break;
            case SM1:
                final List<String> sm1AA = nativeAssets.sm1;
                native_asset = getValueFromArray(sm1AA, indexToPickFromList);
                break;
            default:
                native_asset = "";
                break;
        }
        return native_asset;
    }

    private String getArchivedAsset(final String location, final int indexToPickFromList) {
        String archived_asset = null;

        switch(location) {
            case NEW_YORK:
                final List<String> nyAA = archivedAssets.ny;
                archived_asset = getValueFromArray(nyAA, indexToPickFromList);
                break;
            case ATLANTA:
                final List<String> atlAA = archivedAssets.atlanta;
                archived_asset = getValueFromArray(atlAA, indexToPickFromList);
                break;
            case SM1:
                final List<String> sm1AA = archivedAssets.sm1;
                archived_asset = getValueFromArray(sm1AA, indexToPickFromList);
                break;
            default:
                archived_asset = "";
                break;
        }
        return archived_asset;
    }

    private String getRouter(final String location, final int indexToPickFromList) {
        String router = null;

        switch(location) {
            case LOS_ANGELES:
                final List<String> larouters = routers.losAngeles;
                router = getValueFromArray(larouters, indexToPickFromList);
                break;
            case NEW_YORK:
                final List<String> nyrouters = routers.ny;
                router = getValueFromArray(nyrouters, indexToPickFromList);
                break;
            case ATLANTA_EVERTZ:
                final List<String> atlErouters = routers.atlantaEvertz;
                router = getValueFromArray(atlErouters, indexToPickFromList);
                break;
            case ATLANTA:
                final List<String> atlrouters = routers.atlanta;
                router = getValueFromArray(atlrouters, indexToPickFromList);
                break;
            case SM1:
                final List<String> sm1routers = routers.sm1;
                router = getValueFromArray(sm1routers, indexToPickFromList);
                break;
            default:
                router = "";
                break;
        }
        return router;
    }

    public String fsBaseUrl() {
        String url = null;
        url = getFSBaseUrl(DataMapper.local().midasLocation.toLowerCase());
        return url;
    }

    public String multicityFsBaseUrl() {
        String url = null;
        url = getFSBaseUrl(DataMapper.local().midasMCLocation.toLowerCase());
        return url;
    }

    private String getFSBaseUrl(final String location) {
        String url = null;

        switch(location) {
            case LOS_ANGELES:
                url = fsBaseUrls.losAngeles;
                break;
            case NEW_YORK:
                url = fsBaseUrls.ny;
                break;
            case ATLANTA_EVERTZ:
                url = fsBaseUrls.atlantaEvertz;
                break;
            case ATLANTA:
                url = fsBaseUrls.atlanta;
                break;
            case SM1:
                url = fsBaseUrls.sm1;
                break;
            default:
                url = "";
                break;

        }

        return url;
    }

    public String msBaseUrl(final Boolean isNewUrl) {
        String url = null;
        url = getMSBaseUrl(DataMapper.local().midasLocation.toLowerCase(), isNewUrl);
        return url;
    }

    public String multicityMsBaseUrl(final Boolean isNewUrl) {
        String url = null;
        url = getMSBaseUrl(DataMapper.local().midasMCLocation.toLowerCase(), isNewUrl);
        return url;
    }

    private String getMSBaseUrl(final String location, final Boolean isNewUrl) {
        String url = null;

        switch(location) {
            case LOS_ANGELES:
                if (isNewUrl) {
                    url = msBaseUrlsNew.losAngeles;
                } else {
                    url = msBaseUrls.losAngeles;
                }
                break;
            case NEW_YORK:
                if (isNewUrl) {
                    url = msBaseUrlsNew.ny;
                } else {
                    url = msBaseUrls.ny;
                }
                break;
            case ATLANTA_EVERTZ:
                if (isNewUrl) {
                    url = msBaseUrlsNew.atlantaEvertz;
                } else {
                    url = msBaseUrls.atlantaEvertz;
                }
                break;
            case ATLANTA:
                if (isNewUrl) {
                    url = msBaseUrlsNew.atlanta;
                } else {
                    url = msBaseUrls.atlanta;
                }
                break;
            case SM1:
                if (isNewUrl) {
                    url = msBaseUrlsNew.sm1;
                } else {
                    url = msBaseUrls.sm1;
                }
                break;
            default:
                url = "";
                break;

        }

        return url;
    }

    private String getValueFromArray(final List<String> arrayList, final int index) {

        String value = null;
        if (index <= arrayList.size() - 1) {
            value = arrayList.get(index);
        }
        else {
            value = arrayList.get(0);
        }
        return value;
    }

}

