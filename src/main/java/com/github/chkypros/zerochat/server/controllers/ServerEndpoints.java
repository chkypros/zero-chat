package com.github.chkypros.zerochat.server.controllers;

import com.github.chkypros.zerochat.entities.ConnectRequest;
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
    public Set<User> getConnectedUsers() {
        return service.getConnectedUsers();
    }

    @GetMapping("/check/{username}")
    public boolean usernameExists(@PathVariable("username") String username) {
        return service.usernameExists(username);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        service.registerUser(user);
    }

    @PostMapping("/unregister")
    public void unregisterUser(@RequestBody User user) {
        service.unregisterUser(user);
    }

    @PostMapping("/connect")
    public void connectToUser(@RequestBody ConnectRequest connectRequest) {
        service.connectToUser(connectRequest);
    }
}
