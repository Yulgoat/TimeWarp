package fr.mightycode.cpoo.server.Manager;

import jakarta.transaction.Transactional;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/***
 * JdbcUserDetailsManager but with email more, so we had email in the create database and we add the different access
 * We add the email exist
 * We also add the function of JdbcUserDetailsManager which were private
 */
public class TimeWarpUserDetailsManager extends JdbcUserDetailsManager {
  private String createUserSql = "insert into users (username, email, password, enabled) values (?,?,?,?)";
  private String createAuthoritySql = "insert into authorities (username, authority) values (?,?)";
  private String emailExistsSql = "select email from users where email = ?";
  private String changeUsernameSql = "update users set username = ? where username = ?";

  public TimeWarpUserDetailsManager(DataSource dataSource) {
    super(dataSource);
    this.setDataSource(dataSource);
  }

  /***
   * Create an user in the database
   * @param user TimeWarpUser
   */
  public void createUser(final TimeWarpUser user) {
    this.validateUserDetails(user);
    this.getJdbcTemplate().update(this.createUserSql, (ps) -> {
      ps.setString(1, user.getUsername());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPassword());
      ps.setBoolean(4, user.isEnabled());
      int paramCount = ps.getParameterMetaData().getParameterCount();
      if (paramCount > 4) {
        ps.setBoolean(5, !user.isAccountNonLocked());
        ps.setBoolean(6, !user.isAccountNonExpired());
        ps.setBoolean(7, !user.isCredentialsNonExpired());
      }
    });
    if (this.getEnableAuthorities()) {
      this.insertUserAuthorities(user);
    }
  }

  /***
   * Search in the database if an email already exists
   * @param email String
   * @return true if email already exists / false else
   * @throws IncorrectResultSizeDataAccessException if there are more than one user found with this email
   */
  public boolean emailExists(String email) {
    List<String> emails = this.getJdbcTemplate().queryForList(this.emailExistsSql, new String[]{email}, String.class);
    if (emails.size() > 1) {
      throw new IncorrectResultSizeDataAccessException("More than one user found with name '" + email + "'", 1);
    } else {
      return emails.size() == 1;
    }
  }

  private void validateUserDetails(UserDetails user) {
    Assert.hasText(user.getUsername(), "Username may not be empty or null");
    this.validateAuthorities(user.getAuthorities());
  }

  private void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
    Assert.notNull(authorities, "Authorities list must not be null");
    Iterator var2 = authorities.iterator();

    while (var2.hasNext()) {
      GrantedAuthority authority = (GrantedAuthority) var2.next();
      Assert.notNull(authority, "Authorities list contains a null entry");
      Assert.hasText(authority.getAuthority(), "getAuthority() method must return a non-empty string");
    }
  }

  private void insertUserAuthorities(UserDetails user) {
    Iterator var2 = user.getAuthorities().iterator();

    while (var2.hasNext()) {
      GrantedAuthority auth = (GrantedAuthority) var2.next();
      this.getJdbcTemplate().update(this.createAuthoritySql, new Object[]{user.getUsername(), auth.getAuthority()});
    }
  }

  /***
   * Change username in the table users and authorities
   * @param oldUsername
   * @param newUsername
   */
  @Transactional
  public void changeUsername(String oldUsername, String newUsername) {
    this.getJdbcTemplate().execute("SET REFERENTIAL_INTEGRITY FALSE");
    this.getJdbcTemplate().update(this.changeUsernameSql, newUsername, oldUsername);
    if (this.getEnableAuthorities()) {
      this.updateAuthoritiesUsername(oldUsername, newUsername);
    }
    this.getJdbcTemplate().execute("SET REFERENTIAL_INTEGRITY TRUE");
  }

  private void updateAuthoritiesUsername(String oldUsername, String newUsername) {
    String updateAuthoritiesSql = "update authorities set username = ? where username = ?";
    this.getJdbcTemplate().update(updateAuthoritiesSql, newUsername, oldUsername);
  }
}
