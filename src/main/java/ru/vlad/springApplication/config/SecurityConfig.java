package ru.vlad.springApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vlad.springApplication.models.Role;
import ru.vlad.springApplication.services.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    @SuppressWarnings("MagicNumber")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/list").hasRole(Role.ADMIN.name())
                .antMatchers("/cars/list").hasRole(Role.ADMIN.name())
                .antMatchers("/cars/edit/**").hasRole(Role.ADMIN.name())
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/engine/**").hasRole(Role.ADMIN.name())
                .antMatchers("/wheels/**").hasRole(Role.ADMIN.name())
                .antMatchers("/transmission/**").hasRole(Role.ADMIN.name())
                .antMatchers("/otherOption/**").hasRole(Role.ADMIN.name())
                .antMatchers("/users/**").hasRole(Role.ADMIN.name())
                .antMatchers("/cars/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/**").permitAll()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/cars");
    }

}
