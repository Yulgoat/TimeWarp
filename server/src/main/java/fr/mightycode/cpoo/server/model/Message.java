package fr.mightycode.cpoo.server.model;

import lombok.Data;

@Data
public class Message {
    private String from, to, body;
}
