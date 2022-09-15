package com.project.JobGsm.domain.user;

import com.project.JobGsm.domain.user.enumType.Major;
import com.project.JobGsm.domain.user.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private String refreshToken;

    @Column
    private Major major;

    @Column
    private String github;

    @Column
    private Integer career;

    @Column
    private String discord;

    @Column(name = "Role")
    @Enumerated(STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> role = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken != null ? refreshToken : this.refreshToken;
    }

    public void updatePassword(String password) {
        this.password = password != null ? password : this.password;
    }

    public void updateMajorAndCareer(Major major, int career) {
        this.major = major != null ? major : this.major;
        this.career = career != 0 ? career : this.career;
    }

}
