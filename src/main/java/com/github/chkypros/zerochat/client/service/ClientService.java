package com.github.chkypros.zerochat.client.service;

import com.github.chkypros.zerochat.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private static final String CONNECT_ENDPOINT = "/register";
    private static final String CHECK_USERNAME_ENDPOINT = "/check/{username}";
    private static final String GET_USERS_ENDPOINT = "/users";
    private static final String SUCCESSFUL_CONNECT = "Connected successfully";

    private final String serverUrl;
    private final WebClient webClient;

    public ClientService(@Value("${application.server.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.webClient = WebClient.create(serverUrl);
    }

    public boolean connect(String username) {
        log.info("Contacting server @ {}", serverUrl);
        boolean connectedSuccessfully = false;

        try {
            User userDetails = new User(username, InetAddress.getLocalHost().getHostAddress());

            ClientResponse response = webClient.post()
                    .uri(CONNECT_ENDPOINT)
                    .body(BodyInserters.fromValue(userDetails))
                    .exchange()
                    .block();
            if (null != response) {
                String responseBody = response.bodyToMono(String.class).block();
                if (response.statusCode().is2xxSuccessful() && SUCCESSFUL_CONNECT.equals(responseBody)) {
                    log.info("Connected to server with username: {}", username);
                    connectedSuccessfully = true;
                } else {
                    log.warn("Could not connect to server. Got error response: {}", responseBody);
                }
            } else {
                log.error("Didn't get response from server");
            }
        } catch (UnknownHostException e) {
            log.error("Failed to retrieve local host address");
        }

        return connectedSuccessfully;
    }

    public boolean checkUsername(String username) {
        log.info("Checking if username '{}' exists", username);
        ClientResponse response = webClient.get()
                .uri(CHECK_USERNAME_ENDPOINT, username)
                .exchange()
                .block();
        if (null != response) {
            return response.bodyToMono(Boolean.class).blockOptional().orElse(false);
        } else {
            log.error("Didn't get response from server");
        }

        return false;
    }

    public List<String> getUsers() {
        log.info("Retrieving usernames from server");
        ResponseEntity<List<String>> responseEntity = webClient.get()
                .uri(GET_USERS_ENDPOINT)
                .exchange()
                .block()
                .toEntityList(String.class)
                .block();
        if (null != responseEntity) {
            return responseEntity.getBody();
        } else {
            log.error("Didn't get response from server");
        }

        return Collections.emptyList();
    }
}
