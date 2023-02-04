package com.example.basicApp.security;

import com.example.basicApp.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfiguration {
   private final UserService userDetails;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   public WebSecurity(UserService userDetails, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userDetails = userDetails;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().formLogin();
      return http.build();
   }

   @Bean
   public UserDetailsService userDetailsService() {
      UserDetails user = User.withDefaultPasswordEncoder()
          .username("user")
          .password("password")
          .roles("USER")
          .build();
      return new InMemoryUserDetailsManager(user);
   }

   @Bean
   protected void configure(HttpSecurity http) throws Exception {
      http
          .csrf()
          .disable()
          .authorizeHttpRequests()
          .requestMatchers(HttpMethod.POST, "/users")
          .permitAll()
          .requestMatchers(HttpMethod.GET, "/user/verify-email")
          .permitAll()
          .anyRequest().authenticated().and()
          .addFilter(new AuthenticationFilter((AuthenticationManager) getAuthenticationFilter()))
          .addFilter(new AuthenticationFilter(authentication -> authentication))
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.authorizeHttpRequests();
   }

   @Bean
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder);
   }

   public AuthenticationFilter getAuthenticationFilter() throws Exception {
      final AuthenticationFilter filter = new AuthenticationFilter(authentication -> authentication);
      filter.setFilterProcessesUrl("/users/login");
      return filter;
   }

}
