package com.company.discussion.config;

import com.company.discussion.services.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig<JwtUserDetailsService> extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticate jwtAuthenticate;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder());

    }

    // Instantiates a BCryptPasswordEncoder object for password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // What to do during authentication
    @Bean
    public JwtAuthenticate jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticate();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Routes that will not require JWT
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/authenticate").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/{userid}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/friendRequests").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friendRequests/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friendRequests/{senderId}/{receiverId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/friendRequests/{senderId}/{receiverId}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/friends").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/friends/{requesterId}/{recipientId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friends/{pageId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friends/{requesterId}/{recipientId}").permitAll()
                .antMatchers(HttpMethod.GET, "/posts/{posterid}/{postedid}").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticate).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
