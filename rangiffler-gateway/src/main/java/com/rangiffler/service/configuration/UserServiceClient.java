package com.rangiffler.service.configuration;


import com.rangiffler.model.UserJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "rangiffler-frienddata", url = "${rangiffler-userdata.base-uri}")
public interface UserServiceClient {

    @GetMapping(value = "/currentUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserJson currentUser(@RequestParam String username);

    @PutMapping(value = "/updateUserInfo")
    UserJson updateUserInfo(@RequestBody UserJson user);

    @GetMapping(value = "/allUsers", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserJson> receivePeopleAround(@RequestParam String username);

}
