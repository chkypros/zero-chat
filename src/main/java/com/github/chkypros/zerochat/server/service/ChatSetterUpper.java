package com.github.chkypros.zerochat.server.service;

import com.github.chkypros.zerochat.entities.ConnectRequest;
import com.github.chkypros.zerochat.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("server")
public class ChatSetterUpper {
    private final UserManager userManager;

    public ChatSetterUpper(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setup(ConnectRequest connectRequest) {
        User sender = userManager.findUser(connectRequest.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender Id"));
        User recipient = userManager.findUser(connectRequest.getRecipientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipient Id"));
        log.info("Received connect request: {} -> {}", sender, recipient);

    }
}
