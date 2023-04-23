package com.rangiffler.api.listener;


import com.rangiffler.api.handler.SessionStorageHandler;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class ExtractCodeFromRespListener implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String code = chain.request().url().queryParameter("code");
        if (code != null) {
            SessionStorageHandler.getInstance().addCode(code);
        }
        return chain.proceed(chain.request());
    }
}
