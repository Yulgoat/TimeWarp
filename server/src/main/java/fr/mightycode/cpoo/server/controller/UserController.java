package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.UserDTO;
import fr.mightycode.cpoo.server.service.UserService;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

  private final UserService userService;

  @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void signup(@RequestBody final UserDTO user) {
    try {
      userService.signup(user.login(), user.password());
    } catch (final IllegalArgumentException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot sign up", ex);
    }
  }

  @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void signin(@RequestBody final UserDTO user) {
    try {
      if (!userService.signin(user.login(), user.password()))
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already signed in");
    } catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot sign in", ex);
    }
  }

  @PostMapping(value = "signout")
  public void signout() {
    try {
      if (!userService.signout())
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not signed in");
    } catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot sign in", ex);
    }
  }
}
