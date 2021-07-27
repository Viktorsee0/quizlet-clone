package by.tms.quizletclone.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private boolean active;
    private String activationCode;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE,
//                    CascadeType.REFRESH, CascadeType.PERSIST},
//            fetch = FetchType.LAZY)
//    private Set<LearnModel> models = new HashSet<>();

//    @OneToMany(mappedBy = "user",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE,
//                    CascadeType.REFRESH, CascadeType.PERSIST},
//            fetch = FetchType.LAZY)
//    private Set<Folder> folders = new HashSet<>();

//    @LazyCollection(LazyCollectionOption.FALSE)


//    @OneToMany
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private List<Post> posts = new ArrayList<>();


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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

