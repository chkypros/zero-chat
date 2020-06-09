package com.github.chkypros.zerochat.server.controllers;

import com.github.chkypros.zerochat.entities.ConnectRequest;
import com.github.chkypros.zerochat.entities.User;
import com.github.chkypros.zerochat.server.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@Profile("server")
public class ServerEndpoints {
    private final ServerService service;

    public ServerEndpoints(ServerService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public Set<User> getConnectedUsers() {
        return service.getConnectedUsers();
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        service.registerUser(user);
    }

    @PostMapping("/connect")
    public void connectToUser(@RequestBody ConnectRequest connectRequest) {
        service.connectToUser(connectRequest);
    }
}
