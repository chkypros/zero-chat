package com.github.chkypros.zerochat.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("server")
@SpringBootApplication(scanBasePackages = {
        "com.github.chkypros.zerochat.entities",
        "com.github.chkypros.zerochat.server"
})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
