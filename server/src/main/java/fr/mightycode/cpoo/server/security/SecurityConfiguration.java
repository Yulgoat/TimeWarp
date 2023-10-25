package fr.mightycode.cpoo.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

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
        .requestMatchers(HttpMethod.DELETE, "/user/*").hasRole("ADMIN")
        .requestMatchers("/discussions/**").permitAll()
        .requestMatchers("/error").permitAll()
        .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

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
}
