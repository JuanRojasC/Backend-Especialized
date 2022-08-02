package com.digitalhouse.msusers.application.rest.controller;

import com.digitalhouse.msusers.domain.models.User;
import com.digitalhouse.msusers.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/bills/{userId}")
    public ResponseEntity<User> getBillsByUser(@PathVariable String userId) {
        return ResponseEntity.ok().body(userService.findAllBills(userId));
    }

}
