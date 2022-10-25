package myapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The representation of a group
 */
@Entity(name = "Group")
@Table(name = "TGroup",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id"
                })
        })
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@NamedQuery(
        name="findAllGroup",
        query="select g from Group g"
)
@NamedQuery(
        name = "findGroupsByName",
        query = "select g from Group g where g.name LIKE :groupName"
)
public class Group implements Serializable {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id of the group
     */
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the group
     */
    @Size(min = 1, message = "{group.size}")
    @Basic(optional = false)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    /**
     * The person belonging to the group
     */
    @OneToMany(//
            cascade = CascadeType.MERGE, //
            fetch = FetchType.LAZY, mappedBy = "group")
    @ToString.Exclude
    private Set<Person> persons;

    /**
     * The version
     */
    @Version()
    private long version = 0;

    /**
     * The counter for the update
     */
    @Transient
    public static long updateCounter = 0;

    /**
     * <b>Constructor of Group</b>
     * @param name A name for the group
     */
    public Group(String name){
        super();
        this.name = name;
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
     * Add a person in this group and set the attribute group of the person to this group
     * @param person a person
     */
    public void addPerson(Person person){
        if(persons == null){
            persons = new HashSet<>();
        }
        persons.add(person);
        person.setGroup(this);
    }

    /**
     * Remove a person of this group
     * @param person a person
     */
    public void removePerson(Person person){
        for(Person p : persons){
            if(p.getId() == person.getId()){
                persons.remove(p);
                break;
            }
        }
    }
}
