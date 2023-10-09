package algebra.hr.dal.entity;
//https://www.baeldung.com/jpa-join-column

import algebra.hr.dal.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING) //annotation indicates that the enum will be persisted as a String !
    @Column(name = "authority", length = 50, nullable = false)
    private Role authority;

    //CONSTRUCTORS
    public Authority() {
        System.out.println("Lazy init");
    }

    public Authority(User user, Role authority) {
        this.user = user;
        this.authority = authority;
    }

    // GET AND SET
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", user=" + user +
                ", authority='" + authority + '\'' +
                '}';
    }
}
