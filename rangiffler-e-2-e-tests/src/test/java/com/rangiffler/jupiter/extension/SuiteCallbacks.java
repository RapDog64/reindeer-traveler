package com.rangiffler.jupiter.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import static org.junit.jupiter.api.extension.ExtensionContext.Store;

public interface SuiteCallbacks extends BeforeAllCallback {

    default void beforeSuiteCallback() {}

    default void afterSuiteCallback() {}

    @Override
    default void beforeAll(ExtensionContext context) throws Exception {
        context.getRoot().getStore(Namespace.GLOBAL)
                .getOrComputeIfAbsent(
                        SuiteCallbacks.class,
                        suiteCallbacksClass -> {
                            beforeSuiteCallback();
                            return (Store.CloseableResource) this::afterSuiteCallback;
                        });
    }
}
