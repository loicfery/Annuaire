package myapp.dao;

import myapp.model.Group;
import myapp.model.Person;
import myapp.model.XUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional()
public class DirectoryDao implements IDirectoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Person addPerson(Person person){
        entityManager.persist(person);
        return person;
    }

    public Person findPerson(long id){
        Person person = entityManager.find(Person.class,id);
        if(person != null && person.getGroup() != null) {
            person.getGroup().getPersons().size();
        }
        return person;
    }

    public void updatePerson(Person person){
        entityManager.merge(person);
    }

    public void removePerson(long id){
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }

    @Transactional(readOnly=true)
    public List<Person> findAllPersons(){
        String query = "SELECT p FROM Person p";
        TypedQuery<Person> q = entityManager.createQuery(query,Person.class);
        return q.getResultList();
    }

    @Transactional(readOnly=true)
    public List<Group> findGroupsByName(String groupName){
        return entityManager.createNamedQuery("findGroupsByName")
                .setParameter("groupName",groupName)
                .getResultList();
    }

    public List<Person> findPersonByMail(String mail){
        return entityManager.createNamedQuery("findPersonByMail")
                .setParameter("mail",mail)
                .getResultList();
    }

    public Group addGroup(Group group){
        entityManager.persist(group);
        return group;
    }

    public void removeGroup(Long id){
        Group group = entityManager.find(Group.class,id);
        entityManager.remove(group);
    }

    @Transactional(readOnly=true)
    public Group findGroup(Long id){
        Group group = entityManager.find(Group.class,id);
        group.getPersons().size();
        return group;
    }

    @Transactional(readOnly=true)
    public List<Group> findAllGroups(){
        return entityManager.createNamedQuery("findAllGroup").getResultList();
    }

    @Transactional(readOnly = true)
    public List<Person> findPersonInGroupByName(String groupName, String personName){
        return entityManager.createNamedQuery("findPersonInGroupByName")
                .setParameter("groupName",groupName)
                .setParameter("personName",personName)
                .getResultList();
    }

    public XUser addXUser(XUser xUser){
        entityManager.persist(xUser);
        return xUser;
    }

    @Transactional(readOnly=true)
    public XUser findXUserByUserName(String userName){
       XUser user = entityManager.find(XUser.class,userName);
       return user;
    }
}
