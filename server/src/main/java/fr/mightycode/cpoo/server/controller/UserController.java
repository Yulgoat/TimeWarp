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
  public ResponseEntity<Object> signin(@RequestBody final UserDTO user) {
    ErrorDTO retour = new ErrorDTO();
    try {
      if (!userService.signin(user.username(), user.password())) {
        retour.setStatus(HttpStatus.CONFLICT.value());
        retour.setError("Conflict");
        retour.setMessage("Already signed in");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(retour);// Already signed in (409)
      }
      else{
        retour.setStatus(HttpStatus.OK.value());
        retour.setError("Success");
        retour.setMessage("Successful Login");
        return ResponseEntity.status(HttpStatus.OK).body(retour); // Success (200)
      }
    } catch (final ServletException ex) {
      if (ex.getCause() instanceof BadCredentialsException) {
        retour.setStatus(HttpStatus.UNAUTHORIZED.value());
        retour.setError("UNAUTHORIZED");
        retour.setMessage("Bad credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retour);// UNAUTHORIZED (401)
      }
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Another error has occurred");
  }

  @PostMapping(value = "signout")
  public void signout() {
    try {
      userService.signout();
    }
    catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

/*** Get the current user (Yes it's a Post request we will see that later) ***/
  @PostMapping(value= "currentuser")
  public ResponseEntity<UserDTO> currentuser(Principal user) {
    try {
      UserDTO udto = new UserDTO(user.getName() + "@" + serverDomain,"","");
      return ResponseEntity.status(HttpStatus.OK).body(udto);
    }
    catch (final Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }


  /*** Disconnect function, alternative to the signout version. We send a return 200 if it succeeds ***/
  /*
 @PostMapping(value = "signout2", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ErrorDTO> signout2(@RequestBody final UserDTO user)  {
    try {
      userService.signout2(user.username());
      ErrorDTO success = new ErrorDTO();
      success.setStatus(HttpStatus.OK.value());
      success.setError("Success");
      success.setMessage("Successful Registration");
      return ResponseEntity.status(HttpStatus.OK).body(success); // Success (200)
    } catch (final ServletException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }
  */

  @DeleteMapping(value = "/{username}")
  public void delete(Principal user, @PathVariable("username") String username) {
    if (!userService.delete(username))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
  }

  @PatchMapping(value = "changepwd", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> changepwd(@RequestBody final ChangePasswordDTO pwd, Principal user) {
    try {
      ErrorDTO reponse = new ErrorDTO();
      int i = userService.changePwd(pwd.oldpassword(),pwd.newpassword());
      if(i==0) {
        reponse.setStatus(HttpStatus.OK.value());
        reponse.setError("Success");
        reponse.setMessage("Password change is a success");
        return ResponseEntity.status(HttpStatus.OK).body(reponse); // Success (200)
      }
      else if(i==1) {
        reponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        reponse.setError("UNAUTHORIZED");
        reponse.setMessage("User not logged in");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reponse); // User not logged in
      }
      else if(i==2){
        reponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        reponse.setError("UNAUTHORIZED");
        reponse.setMessage("Incorrect old password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(reponse); // Incorrect old password
      }
      else{
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


}
