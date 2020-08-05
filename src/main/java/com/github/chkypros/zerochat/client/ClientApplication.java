package com.github.chkypros.zerochat.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("client")
@SpringBootApplication(scanBasePackages = {
        "com.github.chkypros.zerochat.entities",
        "com.github.chkypros.zerochat.client"
})
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
