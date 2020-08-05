package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.ChatRequest;
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

    public String setup(ChatRequest chatRequest) {
        User sender = userManager.findUser(chatRequest.senderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender Id"));
        User recipient = userManager.findUser(chatRequest.recipientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipient Id"));
        log.info("Received connect request: {} -> {}", sender, recipient);

        return recipient.ipAddr();
    }
}
