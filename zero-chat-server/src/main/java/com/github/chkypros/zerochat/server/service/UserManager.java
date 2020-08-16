package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    private final Set<User> connectedUsers = new ConcurrentSkipListSet<>();

    public Set<String> getConnectedUsers() {
        return connectedUsers.stream().map(User::getIdentifier).collect(Collectors.toSet());
    }

    public Optional<User> findUser(@NonNull String identifier) {
        return connectedUsers.stream()
                .filter(u -> identifier.equals(u.getIdentifier()))
                .findAny();
    }

    public boolean usernameExists(String username) {
        return findUser(username).isPresent();
    }

    public boolean registerUser(User user) {
        if (usernameExists(user.getIdentifier())) {
            throw new IllegalStateException("User Identifier " + user.getIdentifier() + " is taken.");
        }

        log.info("User {} connected from {}", user.getIdentifier(), user.getIpAddr());
        return connectedUsers.add(user);
    }

    public void unregisterUser(String username) {
        User user = findUser(username)
                .orElseThrow(() -> new IllegalStateException("User Identifier " + username + " does not exist."));
        log.info("User {} from {} disconnected", username, user.getIpAddr());
        connectedUsers.remove(user);
    }
}
