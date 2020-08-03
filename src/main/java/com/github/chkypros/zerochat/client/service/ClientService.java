package com.github.chkypros.zerochat.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final String serverUrl;

    public ClientService(@Value("{application.server.url.base}") String server_url) {
        serverUrl = server_url;
    }

    public boolean connect() {
        log.info("Contacting server @ {}", serverUrl);
        return true;
    }
}
