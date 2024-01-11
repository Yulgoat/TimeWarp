package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.*;
import fr.mightycode.cpoo.server.service.UserService;
import fr.mightycode.cpoo.server.service.UserSettingsService;

import java.security.Principal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserSettingsController {

  private final UserService userService;
  private final UserSettingsService userSettingsService;

  @Autowired
  public UserSettingsController(UserService userService, UserSettingsService userSettingsService) {
    this.userService = userService;
    this.userSettingsService = userSettingsService;
  }

  @GetMapping("/settings")
  public ResponseEntity<UserSettingsDTO> getUserSettings(final Principal user) {
    UserSettingsDTO userSettingsDTO = userSettingsService.getUserSettingsByUsername(user.getName());
    return new ResponseEntity<>(userSettingsDTO, HttpStatus.OK);
  }

  @PatchMapping("/account/changepp")
  public void changeProfilePicture(@RequestBody String imageUrl) {
    // Implement change profile picture logic
  }

  @PatchMapping("/notifications")
  public ResponseEntity<Object> updateNotifications(final Principal user, @RequestBody NotificationsDTO notificationsDTO) {
    // Implement update notifications logic
    userSettingsService.changeNotificationsSettings(user.getName(), notificationsDTO.sounds(), notificationsDTO.badges());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/change-theme")
  public ResponseEntity<Object> changeTheme(final Principal user, @RequestBody String themeId) {
    userSettingsService.changeUserTheme(user.getName(), Integer.parseInt(themeId));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/language")
  public ResponseEntity<Object> changeLanguage(final Principal user, @RequestBody String language) {
    userSettingsService.changeUserLanguage(user.getName(), language);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /***
   * Change the password of the user
   * @param pwd  which is a ChangePasswordDTO
   * @param user which is the user connect in the cookie
   * @return
   * 200 if the password change is a success /
   * 401 if user not logged in /
   * 401 if old password is incorrect /
   * 500 for others issues
   */
  @PatchMapping(value = "changepwd", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> changepwd(@RequestBody final ChangePasswordDTO pwd, Principal user) {
    try {
      ErrorDTO reponse = new ErrorDTO();
      System.out.println(pwd);
      int i = userSettingsService.changePwd(pwd.oldpassword(), pwd.newpassword());
      if (i == 0) {
        reponse.setStatus(HttpStatus.OK.value());
        reponse.setError("Success");
        reponse.setMessage("Password change is a success");
        return ResponseEntity.status(HttpStatus.OK).body(reponse); // Success (200)
      } else if (i == 1) {
        reponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        reponse.setError("UNAUTHORIZED");
        reponse.setMessage("User not logged in");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reponse); // User not logged in
      } else if (i == 2) {
        reponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        reponse.setError("UNAUTHORIZED");
        reponse.setMessage("Incorrect old password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reponse); // Incorrect old password
      } else {
        reponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        reponse.setError("INTERNAL_SERVER_ERROR");
        reponse.setMessage("Others Issues");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponse); // Others Issues
      }
    }
    catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  /***
   * Change Username
   * @param newUser who is an UserDTO with only the username
   * @param user who is the principal user (we get the old username with this)
   * @return
   * 200 if success
   * 409 if the new username already exist
   * 500 for other issues
   */
  @PatchMapping(value = "account/chgusername", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> changeusername(@RequestBody final UserDTO newUser, Principal user) {
    try {
      ErrorDTO reponse = new ErrorDTO();

      if (userSettingsService.changeUsername(user.getName(), newUser.username())) {
        reponse.setStatus(HttpStatus.OK.value());
        reponse.setError("Success");
        reponse.setMessage("Username change is a success");
        return ResponseEntity.status(HttpStatus.OK).body(reponse); // Success (200)
      } else {
        reponse.setStatus(HttpStatus.CONFLICT.value());
        reponse.setError("UNAUTHORIZED");
        reponse.setMessage("New username already exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(reponse);  // New username already exists (409)
      }
    }
    catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }
}
