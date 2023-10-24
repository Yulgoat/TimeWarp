package fr.mightycode.cpoo.server.service;

import TimeWarp.UserDetailsTimeWarp;
import TimeWarp.UserTimeWarp;
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

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsManager userDetailsManager;

  private final HttpServletRequest httpServletRequest;

  public boolean signup(final String username, final String email, final String password) {
    if (userDetailsManager.userExists(username)) //It is possible to have several accounts by email
      return false;
    final UserDetailsTimeWarp user = new UserTimeWarp(username, email, passwordEncoder.encode(password), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    userDetailsManager.createUser(user);
    return true;
  }

  public boolean signin(final String username, final String password) throws ServletException {
    final HttpSession session = httpServletRequest.getSession(false);
    if (session != null)
      return false;
    httpServletRequest.login(username, password);
    httpServletRequest.getSession(true);
    return true;
  }

  public void signout() throws ServletException {
    httpServletRequest.logout();
  }

  public boolean delete(String username) {
    if (!userDetailsManager.userExists(username))
      return false;
    userDetailsManager.deleteUser(username);
    return true;
  }
}
