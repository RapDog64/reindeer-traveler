package com.rangiffler.tests.api;

import com.rangiffler.api.service.PhotoClient;
import com.rangiffler.jupiter.annotation.RestTest;

@RestTest
public abstract class BaseRestTest {

    protected final PhotoClient photoService = new PhotoClient();
}
