package fr.mightycode.cpoo.server.dto;

public record UserSettingsDTO(int theme, String language, boolean unreadBadges, boolean notificationSound,
                              String profileImage) {
}
