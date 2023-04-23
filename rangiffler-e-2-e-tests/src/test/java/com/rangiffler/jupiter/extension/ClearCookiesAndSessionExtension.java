package com.rangiffler.jupiter.extension;

import com.rangiffler.api.handler.CookieHandler;
import com.rangiffler.api.handler.SessionStorageHandler;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ClearCookiesAndSessionExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        CookieHandler.getInstance().flushAll();
        SessionStorageHandler.getInstance().flushAll();
    }
}
