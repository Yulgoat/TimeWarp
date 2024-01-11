package fr.mightycode.cpoo.server.security;

import fr.mightycode.cpoo.server.Manager.TimeWarpUser;
import fr.mightycode.cpoo.server.Manager.TimeWarpUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private DataSource dataSource;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // Ensure that CORS is applied before authentication, so that OPTIONS requests can be processed unauthenticated.
    http.cors(withDefaults());

    // Disable Cross Site Request Forgery protection
    http.csrf(AbstractHttpConfigurer::disable);

    // Configure endpoint protection
    http.authorizeHttpRequests(authorizeRequests ->
      authorizeRequests
        .requestMatchers("/user/signup").permitAll()
        .requestMatchers("/user/signin").permitAll()
        //.requestMatchers("/user/signout").permitAll()
        //.requestMatchers("/user/currentuser").permitAll()
        .requestMatchers(HttpMethod.DELETE, "/user/*").hasRole("ADMIN")
        //.requestMatchers("/discussions/**").permitAll()
        .requestMatchers("/error").permitAll()
        .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  /*
  //Like TimeWarpUserDetailsManager but with the data stock in the memory and no in a database
  @Bean
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.withUsername("user")
      .password(passwordEncoder.encode("user"))
      .roles("USER")
      .build();
    UserDetails admin = User.withUsername("admin")
      .password(passwordEncoder.encode("admin"))
      .roles("USER", "ADMIN")
      .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
  */

  @Bean
  public TimeWarpUserDetailsManager timeWarpUserDetailsManager(PasswordEncoder passwordEncoder) {
    TimeWarpUserDetailsManager userDetailsManager = new TimeWarpUserDetailsManager(dataSource);

    try {
      // Create a user account to be used by end-to-end tests
      TimeWarpUser user = TimeWarpUser
        .withUsernameTW("user")
        .password(passwordEncoder.encode("user"))
        .email("user")
        .roles("USER")
        .build();
      userDetailsManager.createUser(user);
    }
    catch (Exception e) {
      System.out.println("'user' account already created");
      System.out.println(e);
    }

    try {
      // Create a user account to be used by end-to-end tests
      TimeWarpUser user = TimeWarpUser
        .withUsernameTW("a")
        .password(passwordEncoder.encode("a"))
        .email("a@a.fr")
        .roles("USER")
        .build();
      userDetailsManager.createUser(user);
    }
    catch (Exception e) {
      System.out.println("'user' account already created");
      System.out.println(e);
    }

    try {
      // Create a user account to be used by end-to-end tests
      TimeWarpUser user = TimeWarpUser
        .withUsernameTW("b")
        .password(passwordEncoder.encode("b"))
        .email("b@b.fr")
        .roles("USER")
        .build();
      userDetailsManager.createUser(user);
    }
    catch (Exception e) {
      System.out.println("'user' account already created");
      System.out.println(e);
    }

    try {
      // Create an administrator account
      TimeWarpUser admin = TimeWarpUser.withUsernameTW("admin")
        .password(passwordEncoder.encode("admin"))
        .email("admin")
        .roles("USER", "ADMIN")
        .build();
      userDetailsManager.createUser(admin);
    }
    catch (Exception e) {
      System.out.println("'admin' account already created");
      System.out.println(e);
    }

    return userDetailsManager;
  }
}
