package com.example.librarymanagement.common.client;

import com.example.librarymanagement.common.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable("userId") Long userId);
}
