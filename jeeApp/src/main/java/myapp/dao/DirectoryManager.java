package myapp.dao;

import myapp.model.Group;
import myapp.model.Person;
import myapp.model.XUser;
import myapp.web.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.*;

@Component
public class DirectoryManager implements IDirectoryManager {

    @Autowired
    private IDirectoryDao dao;

    @Autowired
    private MyUserDetails myUserDetails;

    private HashMap<String,UserDetails> users = new HashMap<>();

    public void login(Principal principal){
        if(principal != null) {
            if(!users.containsKey(principal.getName())){
                UserDetails user = myUserDetails.loadUserByUsername(principal.getName());
                users.put(user.getUsername(),user);
            }
        }
    }

    public void logout(Principal principal){
        if(principal != null){
            users.remove(principal.getName());
        }
    }

    public Collection<? extends GrantedAuthority> getRoles(Principal principal){
        if(principal != null && users.containsKey(principal.getName())){
            return users.get(principal.getName()).getAuthorities();
        }
        return null;
    }

    public IDirectoryDao getDao(){
        return dao;
    }

    public Person findPerson(Principal user, long personId){
        if(user != null){
            return dao.findPerson(personId);
        }
        return null;
    }

    public Group findGroup(Principal user, long groupId){
        if(user != null){
            return dao.findGroup(groupId);
        }
        return null;
    }

    public void savePerson(Principal user, Person person){
        if(user != null){
            dao.addPerson(person);
        }
    }

    public void saveGroup(Principal user, Group group){
        if(user != null){
            dao.addGroup(group);
        }
    }

    public void removePerson(Principal user, long id){
        if(user != null){
            dao.removePerson(id);
        }
    }

    public void removeGroup(Principal user, long id){
        if(user != null){
            dao.removeGroup(id);
        }
    }

    public List<Person> findAllPersons(Principal user){
        if(user != null){
            return dao.findAllPersons();
        }
        return null;
    }

    public List<Group> findAllGroup(){
        return dao.findAllGroups();
    }

    public List<Group> findGroupsByName(Principal user,String groupName){
        if(user != null){
            return dao.findGroupsByName(groupName);
        }
        return null;
    }

    public void addUser(Person person){
        var xUser = new XUser(person.getMail(),new BCryptPasswordEncoder().encode(person.getPassword()), Set.of("USER"));
        dao.addXUser(xUser);
    }

    public List<Person> findPersonInGroupByName(Principal user,String groupName, String personName){
        if(user != null) {
            return dao.findPersonInGroupByName(groupName, personName);
        }
        return null;
    }
}
