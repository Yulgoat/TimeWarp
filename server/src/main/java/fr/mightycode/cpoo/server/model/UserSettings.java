package fr.mightycode.cpoo.server.model;

import lombok.Data;

import fr.mightycode.cpoo.server.dto.UserSettingsDTO;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "user_settings")
public class UserSettings {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "theme", nullable = false)
  private int theme;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "unreadBadges", nullable = false)
  private boolean unreadBadges;

  @Column(name = "notificationSound", nullable = false)
  private boolean notificationSound;

  @Column(name = "profileImage", nullable = false)
  private String profileImage;

  public UserSettings() {
  }

  public UserSettings(String username) {
    this.username = username;
    this.theme = 0;
    this.language = "";
    this.unreadBadges = true;
    this.notificationSound = true;
    profileImage = "";
  }

  public UserSettingsDTO toDTO() {
    return new UserSettingsDTO(theme, language, unreadBadges, notificationSound, profileImage);
  }
}
