package com.github.chkypros.zerochat.client.window.main;

import com.github.chkypros.zerochat.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Collection;

@Profile("client")
@ShellComponent
public class MainWindowCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowCommands.class);

    private final ClientService clientService;

    @Autowired
    public MainWindowCommands(ClientService clientService) {
        this.clientService = clientService;
    }

    @ShellMethod(value = "Check if a username exists",   key = "check")
    public String checkUserName(String username) {
        LOGGER.info("Checking if username '{}' is avaliable", username);
        return clientService.checkUsername(username)
                ? "Username exists"
                : "Username available";
    }

    @ShellMethod(value = "Connect to the server", key = "connect")
    public void connectToServer(String username) {
        LOGGER.info("Connecting to server");
        clientService.connect(username);
    }

    @ShellMethod(value = "Retrieves list of connected users", key = "users")
    public Collection<String> listUsers() {
        LOGGER.info("Retrieving list of connected users");
        return clientService.getUsers();
    }
}
