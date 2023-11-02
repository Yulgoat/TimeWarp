package TimeWarp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/***
 * Interface that has the same features as org.springframework.security.core.userdetails.User
 * but with Email methods in addition
 ***/
public class UserTimeWarp extends User implements UserDetailsTimeWarp {
    private final  String email;

    public UserTimeWarp(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = email;
    }

    public UserTimeWarp(String username, String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = email;
    }


    @Override
    public String getEmail() {
        return this.email;
    }
}
