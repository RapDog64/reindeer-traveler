package com.rangiffler.tests.api.grpc;

import com.rangiffler.grpc.Photo;
import com.rangiffler.grpc.PhotoRequest;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.ReceiveCountry;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.ReceiverCountryTestInstancePostProcessor;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.api.BaseGrpcTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import static com.rangiffler.model.enums.Country.KAZAKHSTAN;
import static com.rangiffler.utility.DataGenerator.generateGrpcPhoto;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("[API][rangiffler-photo]: Travel photo")
@DisplayName("[API][rangiffler-photo]: Add Travel photo")
@ExtendWith({ReceiverCountryTestInstancePostProcessor.class})
public class AddTravelPhotoTest extends BaseGrpcTest {

    @ReceiveCountry(country = KAZAKHSTAN)
    private CountryJson kazakhstan;

    @Test
    @AllureId("500025")
    @DisplayName("API: Photo Service should add photo for a user")
    @Tags({@Tag("API"), @Tag("gRPC")})
    @Severity(BLOCKER)
    @GenerateUser
    void shouldAddTravelPhotoForUser(@User(selector = Selector.METHOD) UserJson user) {
        final String username = user.getUsername();
        final Photo photo = generateGrpcPhoto(kazakhstan, username);

        final Photo createdPhoto = step("Prepare data", () ->
                photoGrpcClient.addPhoto(PhotoRequest.newBuilder().setPhoto(photo).build())
        );
        final List<Photo> usersPhotos = step("", () ->
                photoGrpcClient.getPhotosForUser(username)
        );
        step("Verify the photo is created ", () -> assertAll(
                        () -> assertEquals(1, usersPhotos.size()),
                        () -> assertEquals(createdPhoto, usersPhotos.get(0))
                )
        );
    }
}
