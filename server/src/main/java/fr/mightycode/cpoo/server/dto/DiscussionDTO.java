package fr.mightycode.cpoo.server.dto;

import java.util.UUID;

public record DiscussionDTO(UUID id, String user1, String user2, boolean unreadMessage) {

}
