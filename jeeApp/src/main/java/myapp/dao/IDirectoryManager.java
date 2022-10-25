package myapp.dao;

import myapp.model.Group;
import myapp.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Component
public interface IDirectoryManager {

    void login(Principal principal);

    void logout(Principal principal);

    Collection<? extends GrantedAuthority> getRoles(Principal principal);

    IDirectoryDao getDao();

    Person findPerson(Principal user, long personId);

    Group findGroup(Principal user, long groupId);

    void savePerson(Principal user, Person person);

    void saveGroup(Principal user, Group group);

    List<Person> findAllPersons(Principal user);

    List<Group> findAllGroup();

    List<Group> findGroupsByName(Principal user,String groupName);

    void addUser(Person person);

    void removePerson(Principal user, long id);

    void removeGroup(Principal user, long id);

    List<Person> findPersonInGroupByName(Principal user,String groupName, String personName);
}
