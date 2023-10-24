package TimeWarp;

import org.springframework.security.core.userdetails.UserDetails;

/***
 * Interface that has the same features as org.springframework.security.core.userdetails.UserDetails
 * but with Email methods in addition
***/
public interface UserDetailsTimeWarp extends UserDetails {
    String getEmail();
}
