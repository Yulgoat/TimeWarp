package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.UserSettings;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, String> {

  UserSettings findByUsername(String username);

  @Transactional
  @Modifying
  @Query("UPDATE UserSettings u SET u.theme = :themeId WHERE u.username = :username")
  void updateUserTheme(@Param("username") String username, @Param("themeId") int themeId);

  @Transactional
  @Modifying
  @Query("UPDATE UserSettings u SET u.notificationSound = :notificationSound, u.unreadBadges = :unreadBadges WHERE u.username = :username")
  void updateUserNotificationsSettings(@Param("username") String username,
                                       @Param("notificationSound") boolean notificationSound,
                                       @Param("unreadBadges") boolean unreadBadges);

  @Transactional
  @Modifying
  @Query("UPDATE UserSettings u SET u.language = :language WHERE u.username = :username")
  void updateUserLanguage(@Param("username") String username,
                          @Param("language") String language);

  @Modifying
  @Query("UPDATE UserSettings u SET u.username = :newUsername WHERE u.username = :oldUsername")
  void updateUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);


}
