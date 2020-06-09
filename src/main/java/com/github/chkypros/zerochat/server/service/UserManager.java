package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Profile("server")
public class UserManager {
    private final Set<User> connectedUsers = new HashSet<>();

    public Set<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void registerUser(User user) {
        log.info("User {} connected from {}", user.getIdentifier(), user.getIpAddr());
        connectedUsers.add(user);
    }

    public void connectToUser(String sender, String recipient) {
        log.info("Received connect request: {} -> {}", sender, recipient);
    }
}
