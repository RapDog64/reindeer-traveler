package com.rangiffler.condition;

import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.impl.CollectionSource;
import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static java.lang.System.lineSeparator;

@ParametersAreNonnullByDefault
public class FriendsSizeMismatch extends UIAssertionError {
    public FriendsSizeMismatch(CollectionSource collection,
                               List<UserJson> expectedFriends, List<UserJson> actualFriends,
                               String explanation, long timeoutMs) {
        super(
                collection.driver(),
                "Spending size mismatch" +
                        lineSeparator() + "Actual: " + actualFriends + ", List size: " + actualFriends.size() +
                        lineSeparator() + "Expected: " + expectedFriends + ", List size: " + expectedFriends.size() +
                        (explanation == null ? "" : lineSeparator() + "Because: " + explanation) +
                        lineSeparator() + "Collection: " + collection.description(),
                expectedFriends, actualFriends,
                timeoutMs
        );
    }
}
