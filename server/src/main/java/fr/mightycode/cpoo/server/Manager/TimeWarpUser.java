package fr.mightycode.cpoo.server.Manager;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

public class TimeWarpUser extends User {
  private String email;

  /***
   * Will create an TimeWarpUser, which is an User but with email more
   * So we call the User constructor
   * Simplified builder's version
   * @param username String
   * @param email String
   * @param password String
   * @param authorities
   */
  public TimeWarpUser(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.email = email;
  }

  /***
   * Will create an TimeWarpUser, which is an User but with email more
   * So we call the User constructor
   * Complete builder's version
   * @param username
   * @param email
   * @param password
   * @param enabled
   * @param accountNonExpired
   * @param credentialsNonExpired
   * @param accountNonLocked
   * @param authorities
   */
  public TimeWarpUser(String username, String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.email = email;
  }

  /***
   * Starts creating a TimeWarpUserBuilder with username as first characteristic
   * @param username String
   * @return a TimeWarpUserBuilder
   */
  public static TimeWarpUserBuilder withUsernameTW(String username) {
    return builderTW().username(username);
  }

  /***
   * Starts creating a TimeWarpUserBuilder
   * @return a TimeWarpUserBuilder
   */
  public static TimeWarpUserBuilder builderTW() {
    return new TimeWarpUserBuilder();
  }

  /***
   * Get the email of the user
   * @return String email
   */
  public String getEmail() {
    return this.email;
  }

  /***
   *  User.UserBuilder but with email
   *  Allow to create a user with different characteristics, then use build to call the TimeWarpUser constructor.
   */
  public static final class TimeWarpUserBuilder {
    private String username;
    private String password;
    private String email;
    private List<GrantedAuthority> authorities = new ArrayList();
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean disabled;
    private Function<String, String> passwordEncoder = (password) -> {
      return password;
    };

    private TimeWarpUserBuilder() {
    }

    public TimeWarpUserBuilder username(String username) {
      Assert.notNull(username, "username cannot be null");
      this.username = username;
      return this;
    }

    public TimeWarpUserBuilder password(String password) {
      Assert.notNull(password, "password cannot be null");
      this.password = password;
      return this;
    }

    public TimeWarpUserBuilder email(String email) {
      Assert.notNull(password, "email cannot be null");
      this.email = email;
      return this;
    }

    public TimeWarpUserBuilder passwordEncoder(Function<String, String> encoder) {
      Assert.notNull(encoder, "encoder cannot be null");
      this.passwordEncoder = encoder;
      return this;
    }

    public TimeWarpUserBuilder roles(String... roles) {
      List<GrantedAuthority> authorities = new ArrayList(roles.length);
      String[] var3 = roles;
      int var4 = roles.length;

      for (int var5 = 0; var5 < var4; ++var5) {
        String role = var3[var5];
        Assert.isTrue(!role.startsWith("ROLE_"), () -> {
          return role + " cannot start with ROLE_ (it is automatically added)";
        });
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
      }

      return this.authorities((Collection) authorities);
    }

    public TimeWarpUserBuilder authorities(GrantedAuthority... authorities) {
      Assert.notNull(authorities, "authorities cannot be null");
      return this.authorities((Collection) Arrays.asList(authorities));
    }

    public TimeWarpUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
      Assert.notNull(authorities, "authorities cannot be null");
      this.authorities = new ArrayList(authorities);
      return this;
    }

    public TimeWarpUserBuilder authorities(String... authorities) {
      Assert.notNull(authorities, "authorities cannot be null");
      return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
    }

    public TimeWarpUserBuilder accountExpired(boolean accountExpired) {
      this.accountExpired = accountExpired;
      return this;
    }

    public TimeWarpUserBuilder accountLocked(boolean accountLocked) {
      this.accountLocked = accountLocked;
      return this;
    }

    public TimeWarpUserBuilder credentialsExpired(boolean credentialsExpired) {
      this.credentialsExpired = credentialsExpired;
      return this;
    }

    public TimeWarpUserBuilder disabled(boolean disabled) {
      this.disabled = disabled;
      return this;
    }

    public TimeWarpUser build() {
      String encodedPassword = (String) this.passwordEncoder.apply(this.password);
      return new TimeWarpUser(this.username, this.email, encodedPassword, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
    }
  }

}
