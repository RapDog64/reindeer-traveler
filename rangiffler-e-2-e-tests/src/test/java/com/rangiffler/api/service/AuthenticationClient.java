package com.rangiffler.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rangiffler.api.listener.AddCookiesReqListener;
import com.rangiffler.api.listener.ExtractCodeFromRespListener;
import com.rangiffler.api.listener.ReceivedCookieRespListener;
import com.rangiffler.api.handler.CookieHandler;
import com.rangiffler.api.handler.SessionStorageHandler;
import io.qameta.allure.Step;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.rangiffler.config.AppConfig.config;

public class AuthenticationClient {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .followRedirects(true)
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(new ReceivedCookieRespListener())
            .addNetworkInterceptor(new AddCookiesReqListener())
            .addNetworkInterceptor(new ExtractCodeFromRespListener())
            .build();

    private final Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(config.authUrl())
            .build();

    private final AuthenticationService authenticationService = retrofit.create(AuthenticationService.class);

    @Step("Send REST GET('/oauth2/authorize') request to the auth service")
    public void authorize() throws Exception {
        SessionStorageHandler.getInstance().init();
        authenticationService.authorize(
                "code",
                "client",
                "openid",
                config.frontUrl() + "authorized",
                SessionStorageHandler.getInstance().getCodeChallenge(),
                "S256"
        ).execute();
    }

    @Step("Send REST POST('/login') request to auth service")
    public Response<Void> login(String username, String password) throws Exception {
        return authenticationService.login(
                CookieHandler.getInstance().getCookieByPart("JSESSIONID"),
                CookieHandler.getInstance().getCookieByPart("XSRF-TOKEN"),
                CookieHandler.getInstance().getCookieValueByPart("XSRF-TOKEN"),
                username,
                password
        ).execute();
    }

    @Step("Send REST POST('/oauth2/token') request to the auth service")
    public JsonNode getToken() throws Exception {
        String basic = "Basic " + Base64.getEncoder().encodeToString("client:secret".getBytes(StandardCharsets.UTF_8));
        return authenticationService.getToken(
                basic,
                "client",
                config.frontUrl() + "authorized",
                "authorization_code",
                SessionStorageHandler.getInstance().getCode(),
                SessionStorageHandler.getInstance().getCodeVerifier()
        ).execute().body();
    }

    @Step("Send REST POST('/register') request to the auth service")
    public Response<Void> register(String username, String password) throws Exception {
        return authenticationService.register(
                CookieHandler.getInstance().getCookieByPart("JSESSIONID"),
                CookieHandler.getInstance().getCookieByPart("XSRF-TOKEN"),
                CookieHandler.getInstance().getCookieValueByPart("XSRF-TOKEN"),
                username,
                password,
                password
        ).execute();
    }
}
