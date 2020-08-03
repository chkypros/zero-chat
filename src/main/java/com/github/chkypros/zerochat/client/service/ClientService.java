package com.github.chkypros.zerochat.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final String serverUrl;

    // TODO Switch to WebSockets
    private RestTemplate restTemplate;

    public ClientService(@Value("{application.server.url}") String server_url) {
        serverUrl = server_url;
    }

    public boolean connect() {
        log.info("Contacting server @ {}", serverUrl);

        // TODO Use RestTemplate to connect to server
        return true;
    }
}
