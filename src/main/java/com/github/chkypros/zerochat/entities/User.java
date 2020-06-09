package com.github.chkypros.zerochat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final String identifier;
    private final String ipAddr;

    public User(User other) {
        this.identifier = other.identifier;
        this.ipAddr = other.ipAddr;
    }
}
