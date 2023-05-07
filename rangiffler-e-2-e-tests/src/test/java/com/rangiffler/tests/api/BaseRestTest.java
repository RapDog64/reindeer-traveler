package com.rangiffler.tests.api;

import com.rangiffler.api.service.PhotoClient;
import com.rangiffler.jupiter.annotation.RestTest;
import com.rangiffler.jupiter.extension.ReceiverCountryTestInstancePostProcessor;
import org.junit.jupiter.api.extension.ExtendWith;

@RestTest
@ExtendWith({ReceiverCountryTestInstancePostProcessor.class})
public abstract class BaseRestTest {

    protected final PhotoClient photoService = new PhotoClient();
}
