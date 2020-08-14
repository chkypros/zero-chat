package com.github.chkypros.zerochat.server.controllers;

import com.github.chkypros.zerochat.entities.ChatRequest;
import com.github.chkypros.zerochat.entities.User;
import com.github.chkypros.zerochat.server.service.ServerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ServerEndpoints {
    private final ServerService service;

    public ServerEndpoints(ServerService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public Set<String> getConnectedUsers() {
        return service.getConnectedUsers();
    }

    @GetMapping("/check/{username}")
    public boolean usernameExists(@PathVariable("username") String username) {
        return service.usernameExists(username);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/unregister")
    public void unregisterUser(@RequestBody User user) {
        service.unregisterUser(user);
    }

    @PostMapping("/connect")
    public String connectToUser(@RequestBody ChatRequest chatRequest) {
        return service.connectToUser(chatRequest);
    }
}
