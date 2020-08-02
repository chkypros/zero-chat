package com.github.chkypros.zerochat.entities;

public record User(String identifier, String ipAddr) implements Comparable<User> {

    public User(User other) {
        this(other.identifier, other.ipAddr);
    }

    @Override
    public int compareTo(User other) {
        return identifier.compareToIgnoreCase(other.identifier);
    }
}
