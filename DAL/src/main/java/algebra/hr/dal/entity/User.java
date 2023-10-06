package algebra.hr.dal.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    //We need these 3 properties because od Spring security configuration later!
    @Id
    @Column(name = "username", length = 255, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    //**************************************************************************
    //Other properties
    @Column(name = "score", nullable = false)
    private int score;

    //https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Authority> authorities = new HashSet<>();

    //CONSTRUCTORS
    public User() {
    }

    public User(String username, String password, boolean enabled, int score) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.score = score;
    }

    //GET AND SET
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", score=" + score +
                ", authorities=" + authorities +
                '}';
    }
}
