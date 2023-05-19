package com.rangiffler.condition;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.CollectionSource;
import com.rangiffler.model.UserJson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class FriendsCondition {

    public static CollectionCondition friends(UserJson... expectedFriends) {
        return new CollectionCondition() {

            @Override
            public void fail(CollectionSource collection, @Nullable List<WebElement> elements, @Nullable Exception lastError, long timeoutMs) {
                if (elements == null || elements.isEmpty()) {
                    ElementNotFound elementNotFound = new ElementNotFound(collection, List.of("Can`t find elements"), lastError);
                    throw elementNotFound;
                } else if (elements.size() != expectedFriends.length) {
                    throw new FriendsSizeMismatch(collection, Arrays.asList(expectedFriends), bindElementsToFriends(elements), explanation, timeoutMs);
                } else {
                    throw new FriendsMismatch(collection, Arrays.asList(expectedFriends), bindElementsToFriends(elements), explanation, timeoutMs);
                }
            }

            @Override
            public boolean test(List<WebElement> elements) {
                // TODO: finish the condition
                if (elements.size() != expectedFriends.length) {
                    return false;
                }
                for (int i = 0; i < expectedFriends.length; i++) {
                    WebElement row = elements.get(i);
                    UserJson expectedFriend = expectedFriends[i];
                    List<WebElement> cells = row.findElements(By.cssSelector("td"));

                }
                return true;
            }

            @Override
            public boolean missingElementSatisfiesCondition() {
                return false;
            }

            private List<UserJson> bindElementsToFriends(List<WebElement> elements) {
                return null;
            }
        };
    }
}
