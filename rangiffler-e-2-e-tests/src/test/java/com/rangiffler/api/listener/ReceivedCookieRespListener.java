package com.rangiffler.api.listener;


import com.rangiffler.api.handler.CookieHandler;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ReceivedCookieRespListener implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        CookieHandler loginDataHolder = CookieHandler.getInstance();
        List<String> headers = response.headers("Set-Cookie");
        List<String> storedCookies = loginDataHolder.getStoredCookies();

        for (String header : headers) {
            String[] setCookie = header.split(";");
            for (String s : setCookie) {
                if (s.contains("XSRF-TOKEN") || s.contains("JSESSIONID")) {
                    String[] keyValuePair = s.split("=");
                    loginDataHolder.removeCookie(keyValuePair[0]);
                    if (keyValuePair.length == 2) {
                        storedCookies.add(keyValuePair[0] + "=" + keyValuePair[1]);
                    }
                }
            }
        }
        loginDataHolder.setCookie(storedCookies);
        return response;
    }
}
