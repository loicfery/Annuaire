package myapp.model;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The representation of a user
 */
@Entity(name = "XUser")
@Table(name = "TXUser",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id"
                })
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUser {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id of the user
     */
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The username of the user
     */
    @Column(name = "userName", length = 200, nullable = false)
    @Id
    String userName;

    /**
     * The password of the user
     */
    @Column(name = "password", length = 200, nullable = false)
    @Basic
    String password;

    /**
     * The roles of the user
     */
    @Column(name = "roles", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> roles;

    /**
     * The version
     */
    @Version()
    private long version = 0;

    /**
     * The update counter
     */
    @Transient
    public static long updateCounter = 0;

    /**
     * <b>Constructor of XUser</b>
     * @param userName the username of a user
     * @param password the password of a user
     * @param roles the roles of a user
     */
    public XUser(String userName, String password, Set<String> roles){
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }


    @PreUpdate
    public void beforeUpdate() {
        System.err.println("PreUpdate of " + this);
    }

    @PostUpdate
    public void afterUpdate() {
        System.err.println("PostUpdate of " + this);
        updateCounter++;
    }
}
