package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Service
@Profile("server")
public class UserManager {
    private final Set<User> connectedUsers = new ConcurrentSkipListSet<>();

    public Set<User> getConnectedUsers() {
        return connectedUsers;
    }

    public Optional<User> findUser(String identifier) {
        return connectedUsers.stream()
                .filter(u -> u.getIdentifier().equals(identifier))
                .findAny();
    }

    public void registerUser(User user) {
        log.info("User {} connected from {}", user.getIdentifier(), user.getIpAddr());
        connectedUsers.add(user);
    }
}
