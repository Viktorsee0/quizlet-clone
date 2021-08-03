package by.tms.quizletclone.entity;


import by.tms.quizletclone.auth.entity.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    @Schema(example = "1")
    private Long id;

    @Schema(example = "Диана")
    @NotNull
    @NotBlank
    private String firstName;

    @Schema(example = "Шурыгина")
    @NotNull @NotBlank
    private String lastName;

    @Schema(example = "example@gmail.com")
    @NotNull @NotBlank
    private String email;

    @Schema(example = "123")
    @Column(name = "password", nullable = false)
    private String password;


    @Schema(example = "ACTIVE")
    @Enumerated(STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Enumerated(STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(userStatus);
    }

}

