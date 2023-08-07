package fr.mightycode.cpoo.router.model;

import lombok.Data;

@Data
public class Message {
    private String from, to, body;
}
