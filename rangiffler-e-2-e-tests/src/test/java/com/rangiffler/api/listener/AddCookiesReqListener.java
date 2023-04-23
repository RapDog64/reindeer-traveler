package com.rangiffler.api.listener;


import com.rangiffler.api.handler.CookieHandler;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddCookiesReqListener implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Map<String, List<String>> allStoredCookies = CookieHandler.getInstance().getAll();
        if (!allStoredCookies.isEmpty()) {
            Request original = chain.request();
            Map<String, List<String>> originalHeadersMap = chain.request()
                    .headers()
                    .toMultimap();

            originalHeadersMap.putAll(allStoredCookies);

            Request request = new Request.Builder()
                    .url(original.url())
                    .headers(Headers.of(originalHeadersMap
                            .entrySet()
                            .stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> String.join(";", e.getValue())))))
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(chain.request());
    }
}
