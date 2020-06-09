package com.github.chkypros.zerochat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectRequest {
    private final String senderId;
    private final String recipientId;
}
