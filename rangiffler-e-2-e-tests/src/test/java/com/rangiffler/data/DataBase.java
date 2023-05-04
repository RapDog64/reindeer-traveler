package com.rangiffler.data;

import com.rangiffler.config.AppConfig;
import org.apache.commons.lang3.StringUtils;

public enum DataBase {
    GEO("jdbc:postgresql://%s/rangiffler-geo"),
    PHOTO("jdbc:postgresql://%s/rangiffler-photo"),
    AUTH("jdbc:postgresql://%s/rangiffler-auth"),
    USERDATA("jdbc:postgresql://%s/rangiffler-userdata");
    private final String url;

    DataBase(String url) {
        this.url = url;
    }

    public String getUrl() {
        return String.format(url, AppConfig.config.getDatabaseUri());
    }

    public String getUrlForP6Spy() {
        return "jdbc:p6spy:" + StringUtils.substringAfter(getUrl(), "jdbc:");
    }
}
