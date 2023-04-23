package com.rangiffler.data;

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

//    private static final Config CFG = Config.getConfig();

    public String getUrl() {
//        return String.format(url, CFG.databaseAddress());
        return null;
    }

    public String getUrlForP6Spy() {
        return "jdbc:p6spy:" + StringUtils.substringAfter(getUrl(), "jdbc:");
    }
}
