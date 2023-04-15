package com.rangiffler.jupiter.extension;

import org.junit.jupiter.api.extension.Extension;

public class CreateUserExtension implements Extension {
    public enum Selector {
        NESTED,
        METHOD;
    }
}
