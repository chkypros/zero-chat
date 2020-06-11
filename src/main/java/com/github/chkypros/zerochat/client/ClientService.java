package com.github.chkypros.zerochat.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {
    private final String serverUrl;

    public ClientService(@Value("{application.server.url.base}") String server_url) {
        serverUrl = server_url;
    }

    public void connect() {
        log.info("Contacting server @ {}", serverUrl);
    }
}
