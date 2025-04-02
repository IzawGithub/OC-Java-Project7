package com.poseidoncapitalsolutions.aggregator.security;

import com.poseidoncapitalsolutions.aggregator.utils.Password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {
    /**
     * @see
     *     https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#jc-httpsecurity
     */
    @Bean
    @NonNull SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**")
                        .permitAll()
                        .requestMatchers("/js/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                // We use standard form based auth
                .formLogin(form -> form.loginPage("/auth/log-in")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/auth/log-out")
                        .logoutSuccessUrl("/")
                        .addLogoutHandler(new HeaderWriterLogoutHandler(
                                new ClearSiteDataHeaderWriter(Directive.ALL)))
                        .permitAll())
                .csrf(Customizer.withDefaults())
                .rememberMe(Customizer.withDefaults())
                .build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) {
        builder.eraseCredentials(false);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        final var parameters = Password.builder();

        return new Argon2PasswordEncoder(
                parameters.getSaltLength(),
                parameters.getDigestLength(),
                parameters.getParallelism(),
                parameters.getMemory(),
                parameters.getIterations());
    }
}
