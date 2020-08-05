package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.ChatRequest;
import com.github.chkypros.zerochat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("server")
public class ServerService {
    private static final Logger log = LoggerFactory.getLogger(ServerService.class);
    private static final String SUCCESSFUL_CONNECT = "Connected successfully";
    public static final String FAILED_CONNECT = "Failed to connect";

    private final UserManager userManager;
    private final ChatSetterUpper chatSetterUpper;

    @Autowired
    public ServerService(UserManager userManager, ChatSetterUpper chatSetterUpper) {
        this.userManager = userManager;
        this.chatSetterUpper = chatSetterUpper;
    }

    public Set<String> getConnectedUsers() {
        return userManager.getConnectedUsers();
    }

    public boolean usernameExists(String username) {
        return userManager.usernameExists(username);
    }

    public String registerUser(User user) {
        log.info("User {} connecting from {}", user.identifier(), user.ipAddr());
        return userManager.registerUser(user)
                ? SUCCESSFUL_CONNECT
                : FAILED_CONNECT;
    }

    public void unregisterUser(User user) {
        log.info("User {} disconnecting", user.identifier());
        userManager.unregisterUser(user);
    }

    public String connectToUser(ChatRequest chatRequest) {
        return chatSetterUpper.setup(chatRequest);
    }
}
