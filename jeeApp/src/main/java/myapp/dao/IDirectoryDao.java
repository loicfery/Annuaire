package myapp.dao;

import myapp.model.Group;
import myapp.model.Person;
import myapp.model.XUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IDirectoryDao {

    Person addPerson(Person person);

    Person findPerson(long id);

    void updatePerson(Person person);

    void removePerson(long id);

    List<Person> findAllPersons();

    List<Group> findGroupsByName(String groupName);

    Group addGroup(Group group);

    void removeGroup(Long id);

    Group findGroup(Long id);

    List<Group> findAllGroups();

    List<Person> findPersonInGroupByName(String groupName, String personName);

    XUser addXUser(XUser xUser);

    XUser findXUserByUserName(String userName);

    List<Person> findPersonByMail(String mail);
}