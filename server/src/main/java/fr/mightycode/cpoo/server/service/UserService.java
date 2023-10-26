package fr.mightycode.cpoo.server.service;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsManager userDetailsManager;

  private final HttpServletRequest httpServletRequest;
  private final Map<String, String> listEmails = new HashMap<String, String>();
  private final Map<String, Boolean> listOfConnect = new HashMap<String, Boolean>();


  /***
   * 0 if Username already exists
   * 1 if Email already exists
   * 2 if it's OK7
   ***/
  public int signup(final String username, final String email, final String password) {
    if (userDetailsManager.userExists(username))
      return 0;
    if (listEmails.containsValue(email))
      return 1;
    listEmails.put(username, email);
    final UserDetails user = new User(username, passwordEncoder.encode(password), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    userDetailsManager.createUser(user);
    return 2;
  }

  /*** For the SignIn function, we use in addition to HttpSession and httpServletRequest a map listOfConnect
   * that associates true or false with username if the user is logged in or not.
   * If we arrive in signin but the value is true, then we return false for the controller to send an error "User Already signed in "
   * ***/
  public boolean signin(final String login, final String password) throws ServletException {
    final HttpSession session = httpServletRequest.getSession(false);
    if (session != null)
      return false;
    if(listOfConnect.containsKey(login)){
      if(listOfConnect.get(login))
        return false;
    }
    httpServletRequest.login(login, password);
    httpServletRequest.getSession(true);
    if(listOfConnect.containsKey(login)){
      listOfConnect.put(login, true);
    }
    else {
      listOfConnect.replace(login, false, true);
    }
    return true;
  }


  public void signout() throws ServletException {
    httpServletRequest.logout();
  }

  /*** Disconnect function, alternative to the signout version.
   * Here, the  value corresponding to the user in listOfConnect is put to false ***/
  public void signout2(String Username) throws ServletException {
    listOfConnect.replace(Username, true, false);
    httpServletRequest.logout();
  }

  public boolean delete(String username) {
    if (!userDetailsManager.userExists(username))
      return false;
    userDetailsManager.deleteUser(username);
    listEmails.remove(username);
    return true;
  }
}
