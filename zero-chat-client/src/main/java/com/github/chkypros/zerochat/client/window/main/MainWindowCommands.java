package com.github.chkypros.zerochat.client.window.main;

import com.github.chkypros.zerochat.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import java.util.Collection;

@ShellComponent
public class MainWindowCommands implements Quit.Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowCommands.class);

    private final ClientService clientService;
    private final ApplicationContext context;

    @Autowired
    public MainWindowCommands(ClientService clientService, ApplicationContext context) {
        this.clientService = clientService;
        this.context = context;
    }

    @ShellMethod(value = "Check if a username exists",   key = "check")
    public String checkUserName(String username) {
        LOGGER.info("Checking if username '{}' is avaliable", username);
        return clientService.checkUsername(username)
                ? "Username exists"
                : "Username available";
    }

    @ShellMethod(value = "Connect to the server", key = {"connect", "signin"})
    public void connectToServer(String username) {
        LOGGER.info("Connecting to server");
        if (clientService.connect(username)) {
            LOGGER.info("Connected successfully");
        } else {
            LOGGER.warn("Failed to connect to server");
        }
    }

    @ShellMethod(value = "Disconnect from the server", key = {"disconnect", "signout"})
    public void disconnectFromServer() {
        LOGGER.info("Disconnecting from server");
        clientService.disconnect();
    }

    @ShellMethod(value = "Retrieves list of connected users", key = "users")
    public Collection<String> listUsers() {
        LOGGER.info("Retrieving list of connected users");
        return clientService.getUsers();
    }

    @ShellMethod(value = "Exit the shell.", key = {"quit", "exit"})
    public void quit() {
        LOGGER.info("Closing client, and notifying server");
        clientService.disconnect();
        SpringApplication.exit(context);
        // FIXME Fix "already closed application context" issue at the end of the method
    }
}
