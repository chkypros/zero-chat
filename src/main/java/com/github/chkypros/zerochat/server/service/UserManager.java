package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
@Profile("server")
public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    private final Set<User> connectedUsers = new ConcurrentSkipListSet<>();

    public Set<String> getConnectedUsers() {
        return connectedUsers.stream().map(User::identifier).collect(Collectors.toSet());
    }

    public Optional<User> findUser(String identifier) {
        return connectedUsers.stream()
                .filter(u -> u.identifier().equals(identifier))
                .findAny();
    }

    public boolean usernameExists(String username) {
        return findUser(username).isPresent();
    }

    public boolean registerUser(User user) {
        if (usernameExists(user.identifier())) {
            throw new IllegalStateException("User Identifier " + user.identifier() + " is taken.");
        }

        log.info("User {} connected from {}", user.identifier(), user.ipAddr());
        return connectedUsers.add(user);
    }

    public void unregisterUser(User user) {
        if (!usernameExists(user.identifier())) {
            throw new IllegalStateException("User Identifier " + user.identifier() + " does not exist.");
        }

        log.info("User {} disconnected from {}", user.identifier(), user.ipAddr());
        connectedUsers.remove(user);
    }
}
