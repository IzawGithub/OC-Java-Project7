package com.poseidoncapitalsolutions.aggregator.domains;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;
import com.poseidoncapitalsolutions.aggregator.utils.Password;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import net.xyzsd.dichotomy.Maybe;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements IDomain, UserDetails {
    @Builder.Default
    @Id
    @NonNull private UUID id = UUID.randomUUID();

    private String username;

    @Embedded
    private Password password;

    private String fullname;
    private String role;

    // -- impl UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return Maybe.ofNullable(password).map(Password::getHashed).orElse("");
    }

    @SneakyThrows
    public void setPassword(String password) {
        this.password = new Password(password);
    }
}
