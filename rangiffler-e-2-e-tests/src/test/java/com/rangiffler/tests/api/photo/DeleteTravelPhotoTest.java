package com.rangiffler.tests.api.photo;

import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.TravelPhotos;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import com.rangiffler.model.PhotoServiceJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.api.BaseRestTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.rangiffler.model.enums.Country.KAZAKHSTAN;
import static com.rangiffler.tests.message.Message.PHOTO_NOT_FOUND;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("[API][rangiffler-photo]: Travel photo")
@DisplayName("[API][rangiffler-photo]: Delete Travel photo")
public class DeleteTravelPhotoTest extends BaseRestTest {

    @Test
    @AllureId("500029")
    @DisplayName("API: Photo service should delete the user's travel photo")
    @Tag("API")
    @Severity(BLOCKER)
    @GenerateUser(travels = @TravelPhotos(country = KAZAKHSTAN, count = 1))
    void shouldDeleteTravelPhoto(@User(selector = Selector.METHOD) UserJson user) throws IOException {
        final String username = user.getUsername();
        final PhotoServiceJson currentPhoto = photoService.getPhotosForUser(username).get(0);

        final int statusCode = photoService.deleteTravelPhoto(currentPhoto.getId()).code();

        step("Validate the travel photo has been removed", () -> {
            List<PhotoServiceJson> photos = photoService.getPhotosForUser(username);
            assertAll(
                    () -> assertEquals(0, photos.size()),
                    () -> assertEquals(200, statusCode)
            );
        });
    }

    @Test
    @AllureId("500030")
    @Tag("API")
    @Severity(CRITICAL)
    @DisplayName("API: Photo service should return 'photo is not found' message")
    @GenerateUser
    void shouldReturnPhotoNotFound(@User(selector = Selector.METHOD) UserJson user) throws IOException {
        final UUID id = UUID.randomUUID();
        final String expectedMessage = String.format(PHOTO_NOT_FOUND, id);

        final String message = photoService.deleteTravelPhoto(id)
                .errorBody()
                .string()
                .substring(10, 74);

        step("Validate 'Photo is not found' message is displayed", () ->
                assertEquals(expectedMessage, message)
        );
    }
}
