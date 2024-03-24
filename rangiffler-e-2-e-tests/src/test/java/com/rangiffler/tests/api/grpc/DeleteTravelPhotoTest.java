package com.rangiffler.tests.api.grpc;

import com.rangiffler.grpc.NullableId;
import com.rangiffler.grpc.Photo;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.TravelPhotos;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.api.BaseGrpcTest;
import io.grpc.StatusRuntimeException;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.rangiffler.model.enums.Country.KAZAKHSTAN;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("[API][rangiffler-photo]: Travel photo")
@DisplayName("[API][rangiffler-photo]: Delete Travel photo")
public class DeleteTravelPhotoTest extends BaseGrpcTest {

    @Test
    @AllureId("500029")
    @DisplayName("API: Photo service should delete the user's travel photo")
    @Tags({@Tag("API"), @Tag("gRPC")})
    @Severity(BLOCKER)
    @GenerateUser(travels = @TravelPhotos(country = KAZAKHSTAN, count = 1))
    void verifyPhotoTravelPhotoIsDeleted(@User(selector = Selector.METHOD) UserJson user) {
        final String username = user.getUsername();
        final Photo currentPhoto = photoGrpcClient.getPhotosForUser(username).get(0);

        step("Removing a travel photo", () ->
                photoGrpcClient.deleteTravelPhoto(currentPhoto.getId())
        );
        final List<Photo> photos = step("Receiving all the user's travel photos", () ->
                photoGrpcClient.getPhotosForUser(username)
        );
        step("Verify the photo is removed", () -> {
            assertEquals(0, photos.size());
        });
    }

    @Test
    @AllureId("500030")
    @Tags({@Tag("API"), @Tag("gRPC")})
    @Severity(CRITICAL)
    @DisplayName("API: Photo service should return 'photo is not found' message")
    @GenerateUser
    void verifyPhotoNotFoundIsReturnedTest(@User(selector = Selector.METHOD) UserJson user) {
        final String id = UUID.randomUUID().toString();
        final String expectedMessage = String.format("NOT_FOUND: Photo with id %s is not found.", id);

        var message = step("Removing a travel photo", () -> assertThrows(StatusRuntimeException.class,
                () -> photoGrpcClient.deleteTravelPhoto(NullableId.newBuilder().setId(id).build()))
        );
        step("Verify the error message is present", () ->
                assertEquals(expectedMessage, message.getMessage())
        );
    }
}
