package com.github.chkypros.zerochat.client.window.main;

import com.github.chkypros.zerochat.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MainWindowCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowCommands.class);

    private final ClientService clientService;

    @Autowired
    public MainWindowCommands(ClientService clientService) {
        this.clientService = clientService;
    }

    @ShellMethod(value = "Connect to the server", key = "connect")
    public void connectToServer() {
        LOGGER.info("Connecting to server");
        clientService.connect();
    }
}
