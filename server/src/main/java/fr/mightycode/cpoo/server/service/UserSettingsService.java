package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.Manager.TimeWarpUserDetailsManager;
import fr.mightycode.cpoo.server.dto.UserSettingsDTO;
import fr.mightycode.cpoo.server.model.UserSettings;
import fr.mightycode.cpoo.server.repository.DiscussionRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.UserSettingsRepository;
import jakarta.servlet.ServletException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService {

  private final UserSettingsRepository userSettingsRepository;

  @Autowired
  private TimeWarpUserDetailsManager timeWarpUserDetailsManager;
  private final DiscussionRepository discussionRepository;
  private final MessageRepository messageRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  @Autowired
  public UserSettingsService(UserSettingsRepository userSettingsRepository, DiscussionRepository discussionRepository, MessageRepository messageRepository, PasswordEncoder passwordEncoder) {
    this.userSettingsRepository = userSettingsRepository;
    this.discussionRepository = discussionRepository;
    this.messageRepository = messageRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserSettingsDTO getUserSettingsByUsername(String username) {
    UserSettings userSettings = userSettingsRepository.findByUsername(username);
    if (userSettings == null) {
      userSettings = new UserSettings(username);
      userSettingsRepository.save(userSettings);
    }
    System.out.println(userSettings);
    return userSettings.toDTO();
  }

  @Transactional
  public void changeUserTheme(String username, int themeId) {
    userSettingsRepository.updateUserTheme(username, themeId);
  }

  @Transactional
  public void changeNotificationsSettings(String username, boolean notificationSounds, boolean unreadBadges) {
    userSettingsRepository.updateUserNotificationsSettings(username, notificationSounds, unreadBadges);
  }

  @Transactional
  public void changeUserLanguage(String username, String language) {
    userSettingsRepository.updateUserLanguage(username, language);
  }

  /***
   * @param oldPwd
   * @param newPwd
   * @return
   * 0 if it's OK /
   * 1 User not logged in /
   * 2 if Incorrect Old Password
   * @throws ServletException
   */
  public int changePwd(String oldPwd, String newPwd) throws ServletException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
      return 1; // User not logged in
    }

    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
    UserDetails userDetails = (UserDetails) token.getPrincipal();
    String username = userDetails.getUsername();
    UserDetails storedUserDetails = timeWarpUserDetailsManager.loadUserByUsername(username); // Get User details thanks to userDetailsManager

    if (!(passwordEncoder.matches(oldPwd, storedUserDetails.getPassword()))) {
      return 2; // Incorrect old password
    }

    timeWarpUserDetailsManager.changePassword(passwordEncoder.encode(oldPwd), passwordEncoder.encode(newPwd));

    UserDetails updatedUser = new User(username, passwordEncoder.encode(newPwd), userDetails.getAuthorities());
    UsernamePasswordAuthenticationToken updatedToken = new UsernamePasswordAuthenticationToken(updatedUser, token.getCredentials(), updatedUser.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(updatedToken);

    return 0; // Password change is a success
  }

  /***
   * Change Username into all the tables of the database
   * @param oldUsername
   * @param newUsername
   * @return
   * true if success
   * false if new username already exists
   */
  @Transactional
  public boolean changeUsername(String oldUsername, String newUsername) {
    if (timeWarpUserDetailsManager.userExists(newUsername)) {
      return false;
    }

    timeWarpUserDetailsManager.changeUsername(oldUsername, newUsername);

    discussionRepository.updateUsernameInUser1(oldUsername + "@" + serverDomain, newUsername + "@" + serverDomain);
    discussionRepository.updateUsernameInUser2(oldUsername + "@" + serverDomain, newUsername + "@" + serverDomain);

    userSettingsRepository.updateUsername(oldUsername, newUsername);

    messageRepository.updateUsernameFrom(oldUsername + "@" + serverDomain, newUsername + "@" + serverDomain);
    messageRepository.updateUsernameTo(oldUsername + "@" + serverDomain, newUsername + "@" + serverDomain);

    return true; //Success
  }
}
