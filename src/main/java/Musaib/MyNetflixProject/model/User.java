package Musaib.MyNetflixProject.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotEmpty
    @Size(min =4,max =20,message ="name must be min of four of size and max 20")
    private  String name;

    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String roles;
    private Boolean subscription;
    private Boolean emailVerification;
    private Boolean phoneNumberVerification;
    private AccountStatus accountStatus;

    private String userOtpGenerated;
    private  Long otpValidity;

    private  String password;
    @OneToMany(mappedBy ="user" )
    private Set<Profile> profile;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
           return  Collections.singletonList(new SimpleGrantedAuthority(this.roles));
        }

        @Override
        public String getUsername() {
            return this.email;
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

