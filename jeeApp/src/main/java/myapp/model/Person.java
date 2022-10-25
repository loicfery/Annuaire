package myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The representation of a person
 */
@Entity(name = "Person")
@Table(name = "TPerson",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id"
                })
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "findPersonInGroupByName",
        query = "SELECT p FROM Person p WHERE p in (SELECT g.persons FROM Group g WHERE g.name = :groupName) and p.firstName LIKE :personName"
)
@NamedQuery(
        name = "findPersonByMail",
        query = "SELECT p FROM Person p WHERE p.mail = :mail"
)

public class Person implements Serializable {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id of the persoon
     */
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The first name of the person
     */
    @Basic(optional = false)
    @Column(name = "first_name", length = 200, nullable = false)
    @Size(min = 1, message = "{person.size}")
    private String firstName;

    /**
     * The last name of the person
     */
    @Size(min = 1, message = "{person.size}")
    @Basic(optional = false)
    @Column(name = "last_name", length = 200, nullable = false)
    private String lastName;

    /**
     * The web of the person
     */
    @Basic
    @Column(name = "web", length = 200)
    private String web;

    /**
     * the mail of the person
     */
    @Size(min = 1, message = "{person.size}")
    @Basic
    @Column(name = "mail", length = 200)
    private String mail;

    /**
     * The birthday of the person
     */
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day", nullable = false)
    private Date birthday;

    /**
     * The password of the person
     */
    @Size(min = 5, message = "{person.size}")
    @Basic(optional = false)
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    /**
     * The group where the person belong
     */
    @ManyToOne
    @JoinColumn(name = "pgroup")
    @ToString.Exclude
    private Group group;

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
     * <b>Constructor of Person</b>
     * @param firstName the first name of a person
     * @param lastName the last name of a person
     * @param password the password of a person
     */
    public Person(String firstName, String lastName,String password){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /**
     * <b>Constructor of Person</b>
     * @param firstName the first name of a person
     * @param lastName the last name of a person
     * @param password the password of a person
     * @param web the web of a person
     * @param mail the mail of a person
     * @param birthday the birthday of a person
     */
    public Person(String firstName, String lastName, String password, String web, String mail, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.web = web;
        this.mail = mail;
        this.birthday = birthday;
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

    /**
     * Convert the date in a string to a date format 'yyy-MM-dd' and set the birthday with it
     * @param birthday the birthday in a string
     */
    public void setBirthday(String birthday){
        if(birthday != null) {
            try {
                this.birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        else{
            this.birthday = null;
        }
    }

    /**
     * Return the birthday to a string format with a conversion
     * @return a string value of the birthday
     */
    public String getBirthday(){
        if(birthday != null){
            return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
        }
        return null;
    }
}
