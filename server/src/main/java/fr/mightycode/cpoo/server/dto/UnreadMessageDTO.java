package fr.mightycode.cpoo.server.dto;

import java.util.UUID;

public record UnreadMessageDTO(UUID discussionId, boolean unreadMessage) {
}