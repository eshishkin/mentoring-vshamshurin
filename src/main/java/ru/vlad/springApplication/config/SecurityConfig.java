package ru.vlad.springApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.vlad.springApplication.handler.CustomAccessDeniedHandler;
import ru.vlad.springApplication.models.Role;
import ru.vlad.springApplication.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PATH = "/login";

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

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @SuppressWarnings(value = "MultipleStringLiterals")
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
                .antMatchers("/aspect/get_cache").hasRole(Role.ADMIN.name())
                .antMatchers("/cars/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/register").permitAll()
                .antMatchers("/**").permitAll()
                .and().formLogin()
                .loginPage(LOGIN_PATH)
                .loginProcessingUrl("/perform-login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/cars")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(LOGIN_PATH);
    }

}
