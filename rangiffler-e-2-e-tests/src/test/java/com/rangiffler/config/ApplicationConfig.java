package com.rangiffler.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties"
})
public interface ApplicationConfig extends Config {

    String PROJECT_NAME = "rangiffler";

    @Key("front.url")
    String frontUrl();

    @Key("auth.url")
    String authUrl();

    @Key("gateway.url")
    String gatewayUrl();

    @Key("photo.url")
    String photoUrl();

    @Key("geo.url")
    String geoUrl();

    @Key("userdata.url")
    String userdataUrl();

    @Key("database.url")
    String databaseAddress();

}
