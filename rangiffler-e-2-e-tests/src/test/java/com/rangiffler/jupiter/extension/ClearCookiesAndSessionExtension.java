package com.rangiffler.jupiter.extension;

import com.rangiffler.api.manager.CookieHolder;
import com.rangiffler.api.manager.SessionStorageHolder;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ClearCookiesAndSessionExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        CookieHolder.getInstance().flushAll();
        SessionStorageHolder.getInstance().flushAll();
    }
}
