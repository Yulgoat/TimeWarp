package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ChangePasswordDTO;
import fr.mightycode.cpoo.server.dto.ErrorDTO;
import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.service.UserService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  private final UserService userService;

  /***
   * Create the user
   * @param user which is an UserDTO
   * @return
   * 200 if SignUp is a success /
   * 409 with Username Already Exists /
   * 409 with Email Already Exists /
   * 500 for others issues
   */
  @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> signup(@RequestBody final UserDTO user) {
    int res = userService.signup(user.username(), user.email(), user.password());
    if (res == 2) {
      ErrorDTO success = new ErrorDTO();
      success.setStatus(HttpStatus.OK.value());
      success.setError("Success");
      success.setMessage("Successful Registration");
      return ResponseEntity.status(HttpStatus.OK).body(success); // Success (200)
    } else if (res == 0) {
      ErrorDTO error = new ErrorDTO();
      error.setStatus(HttpStatus.CONFLICT.value());
      error.setError("Conflict");
      error.setMessage("Username already exists");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);// Username Already Exists (409)
    } else if (res == 1) {
      ErrorDTO error = new ErrorDTO();
      error.setStatus(HttpStatus.CONFLICT.value());
      error.setError("Conflict");
      error.setMessage("Email already exists");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);// Username Already Exists (409)
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Another error has occurred");
    }
  }

  /***
   * Connect the user
   * @param user which is an UserDTO
   * @return
   * 409 if user already signed in /
   * 200 if success /
   * 401 if bad credentials /
   * 500 for others issues
   */
  @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> signin(@RequestBody final UserDTO user) {
    ErrorDTO retour = new ErrorDTO();
    try {
      if (!userService.signin(user.username(), user.password())) {
        retour.setStatus(HttpStatus.CONFLICT.value());
        retour.setError("Conflict");
        retour.setMessage("Already signed in");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(retour);// Already signed in (409)
      } else {
        retour.setStatus(HttpStatus.OK.value());
        retour.setError("Success");
        retour.setMessage("Successful Login");
        return ResponseEntity.status(HttpStatus.OK).body(retour); // Success (200)
      }
    }
    catch (final ServletException ex) {
      if (ex.getCause() instanceof BadCredentialsException) {
        retour.setStatus(HttpStatus.UNAUTHORIZED.value());
        retour.setError("UNAUTHORIZED");
        retour.setMessage("Bad credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retour);// UNAUTHORIZED (401)
      }
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Another error has occurred");
  }

  /***
   * Disconnect the User
   * @throws Exception   if there is a problem
   */
  @PostMapping(value = "signout")
  public void signout() {
    try {
      userService.signout();
    }
    catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  /***
   * Get the current user (Yes it's a Post request we will see that later)
   * @param user which is the user connect in the cookie
   * @return
   * 200 with the current user /
   * 500 for an error
   */
  @PostMapping(value = "currentuser")
  public ResponseEntity<UserDTO> currentuser(Principal user) {
    try {
      UserDTO udto = new UserDTO(user.getName() + "@" + serverDomain, "", "");
      return ResponseEntity.status(HttpStatus.OK).body(udto);
    }
    catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @DeleteMapping(value = "/{username}")
  public void delete(Principal user, @PathVariable("username") String username) {
    if (!userService.delete(username))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
  }

}
