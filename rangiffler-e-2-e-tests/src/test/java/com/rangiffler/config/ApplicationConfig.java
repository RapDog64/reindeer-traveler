package com.rangiffler.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadPolicy;
import static org.aeonbits.owner.Config.LoadType;
import static org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "classpath:config/${env}-config.properties"
})
public interface ApplicationConfig extends Config {

    @Key("front.url")
    @DefaultValue("http://127.0.0.1:3001/")
    String frontUrl();

    @Key("auth.url")
    @DefaultValue("http://127.0.0.1:9000/")
    String authUrl();

    @Key("gateway.url")
    @DefaultValue("http://127.0.0.1:8080/")
    String gatewayUrl();

    @Key("photo.url")
    @DefaultValue("http://127.0.0.1:8086/")
    String photoUrl();

    @Key("geo.url")
    @DefaultValue("http://127.0.0.1:8088/")
    String geoUrl();

    @Key("userdata.url")
    @DefaultValue("http://127.0.0.1:8089/")
    String userdataUrl();

    @Key("database.url")
    @DefaultValue("127.0.0.1:5432")
    String getDatabaseUri();

}
