package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.ConnectRequest;
import com.github.chkypros.zerochat.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Profile("server")
public class ServerService {
    private final UserManager userManager;

    @Autowired
    public ServerService(UserManager userManager) {
        this.userManager = userManager;
    }

    public Set<User> getConnectedUsers() {
        return userManager.getConnectedUsers();
    }

    public void registerUser(User user) {
        Optional<User> existingUser = userManager.findUser(user.getIdentifier());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("User Identifier " + user.getIdentifier() + " is taken.");
        }

        log.info("User {} connected from {}", user.getIdentifier(), user.getIpAddr());
        userManager.registerUser(user);
    }

    public void connectToUser(ConnectRequest connectRequest) {
        String sender = connectRequest.getSenderId();
        String recipient = connectRequest.getRecipientId();
        log.info("Received connect request: {} -> {}", sender, recipient);
        userManager.connectToUser(sender, recipient);
    }
}
