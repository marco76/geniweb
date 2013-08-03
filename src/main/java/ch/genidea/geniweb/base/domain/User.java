package ch.genidea.geniweb.base.domain;

import ch.genidea.geniweb.base.domain.Role;
import ch.genidea.geniweb.base.domain.SecurityCode;

import javax.persistence.*;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "screen_name")
    private String screeName;
    private String password;
    @Column(name = "username", unique = true)
    private String username;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private SecurityCode securityCode;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean enabled;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private Role role;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public SecurityCode getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(SecurityCode securityCode) {
        this.securityCode = securityCode;
    }

    public String getScreeName() {
        return screeName;
    }

    public void setScreeName(String screeName) {
        this.screeName = screeName;
    }
}
