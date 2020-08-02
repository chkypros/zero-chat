package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.ConnectRequest;
import com.github.chkypros.zerochat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("server")
public class ChatSetterUpper {
    private static final Logger log = LoggerFactory.getLogger(ChatSetterUpper.class);

    private final UserManager userManager;

    public ChatSetterUpper(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setup(ConnectRequest connectRequest) {
        User sender = userManager.findUser(connectRequest.senderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender Id"));
        User recipient = userManager.findUser(connectRequest.recipientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipient Id"));
        log.info("Received connect request: {} -> {}", sender, recipient);

    }
}
