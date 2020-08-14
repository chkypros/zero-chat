package com.github.chkypros.zerochat.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record User(@JsonProperty("identifier") String identifier, @JsonProperty("ipAddr") String ipAddr) implements Comparable<User>, Serializable {

    public User(User other) {
        this(other.identifier, other.ipAddr);
    }

    @Override
    public int compareTo(User other) {
        return identifier.compareToIgnoreCase(other.identifier);
    }
}
