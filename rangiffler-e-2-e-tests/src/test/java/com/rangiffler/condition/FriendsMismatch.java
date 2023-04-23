package com.rangiffler.condition;

import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.impl.CollectionSource;
import com.rangiffler.model.UserJson;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static java.lang.System.lineSeparator;

@ParametersAreNonnullByDefault
public class FriendsMismatch extends UIAssertionError {

    public FriendsMismatch(CollectionSource collection,
                           List<UserJson> expectedFriends, List<UserJson> actualFriends,
                           String explanation, long timeoutMs) {
        super(
                collection.driver(),
                "Spending mismatch" +
                        lineSeparator() + "Actual: " + actualFriends +
                        lineSeparator() + "Expected: " + expectedFriends +
                        (explanation == null ? "" : lineSeparator() + "Because: " + explanation) +
                        lineSeparator() + "Collection: " + collection.description(),
                expectedFriends, actualFriends,
                timeoutMs);
    }
}
