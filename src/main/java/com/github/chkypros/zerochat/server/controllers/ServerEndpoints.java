package com.github.chkypros.zerochat.server.controllers;

import com.github.chkypros.zerochat.entities.ChatRequest;
import com.github.chkypros.zerochat.entities.User;
import com.github.chkypros.zerochat.server.service.ServerService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Profile("server")
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
