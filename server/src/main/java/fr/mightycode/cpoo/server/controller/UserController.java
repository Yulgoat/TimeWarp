package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.ErrorDTO;
import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.service.UserService;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

  private final UserService userService;

  @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> signup(@RequestBody final UserDTO user) {
    int res = userService.signup(user.username(), user.email(), user.password());
    if (res == 2) {
      ErrorDTO success = new ErrorDTO();
      success.setStatus(HttpStatus.OK.value());
      success.setError("Success");
      success.setMessage("Successful Registration");
      return ResponseEntity.status(HttpStatus.OK).body(success); // Success (200)
    }
    else if (res == 0) {
      ErrorDTO error = new ErrorDTO();
      error.setStatus(HttpStatus.CONFLICT.value());
      error.setError("Conflict");
      error.setMessage("Username already exists");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);// Username Already Exists (409)
    }
    else if (res == 1) {
      ErrorDTO error = new ErrorDTO();
      error.setStatus(HttpStatus.CONFLICT.value());
      error.setError("Conflict");
      error.setMessage("Email already exists");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);// Username Already Exists (409)
    }
    else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Another error has occurred");
    }
  }


  @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void signin(@RequestBody final UserDTO user) {
    try {
      if (!userService.signin(user.username(), user.password()))
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Already signed in");
    } catch (final ServletException ex) {
      if (ex.getCause() instanceof BadCredentialsException)
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @PostMapping(value = "signout")
  public void signout() {
    try {
      userService.signout();
    } catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

  @DeleteMapping(value = "/{username}")
  public void delete(Principal user, @PathVariable("username") String username) {
    if (!userService.delete(username))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
  }
}
