package com.github.chkypros.zerochat.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.github.chkypros.zerochat.entities",
        "com.github.chkypros.zerochat.client"
})
public class ClientApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
