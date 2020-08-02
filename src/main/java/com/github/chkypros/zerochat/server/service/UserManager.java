package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
@Profile("server")
public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    private final Set<User> connectedUsers = new ConcurrentSkipListSet<>();

    public Set<User> getConnectedUsers() {
        return connectedUsers;
    }

    public Optional<User> findUser(String identifier) {
        return connectedUsers.stream()
                .filter(u -> u.identifier().equals(identifier))
                .findAny();
    }

    public void registerUser(User user) {
        log.info("User {} connected from {}", user.identifier(), user.ipAddr());
        connectedUsers.add(user);
    }
}
