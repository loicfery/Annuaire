package myapp.web;

import myapp.EntityGeneratorInit;
import myapp.dao.IDirectoryManager;
import myapp.model.Group;
import myapp.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonControler {

    @Autowired
    IDirectoryManager directoryManager;

    @Autowired
    PersonValidator personValidator;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init(){
        EntityGeneratorInit entityGeneratorInit = EntityGeneratorInit.getEntityGeneratorInit(directoryManager.getDao());
        entityGeneratorInit.init();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPersons(){
        logger.info("List of persons");
        return new ModelAndView("personsList");
    }

    @ModelAttribute("persons")
    List<Person> persons(Principal user){
       logger.info("Making list of persons");
        return directoryManager.findAllPersons(user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView newPerson(@ModelAttribute Person person){
        return new ModelAndView("personForm");
    }

    @ModelAttribute
    public Person newPerson(@RequestParam(value = "id", required = false) Long id, Principal principal){
        if(id != null){
            logger.info("find person " + id);
            var person = directoryManager.findPerson(principal,id);
            if(person != null){
                return person;
            }
        }
        Person person = new Person();
        person.setFirstName("");
        person.setLastName("");
        person.setWeb("");
        person.setMail("");
        person.setBirthday(null);
        return person;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView savePerson(@ModelAttribute("person") @Valid Person person, BindingResult result, Principal user){
        personValidator.validate(person,result);
        if(result.hasErrors()){
            return new ModelAndView("personForm");
        }
        var group = directoryManager.findGroup(user,Long.parseLong(person.getGroup().getName()));
        if(group != null) {
            group.addPerson(person);
            directoryManager.savePerson(user,person);
        }
        return new ModelAndView("personsList");
    }

    @ModelAttribute("groupSelection")
    public Map<Long, String> groupSelection(){
        Map<Long, String> groups = new LinkedHashMap<>();
        List<Group> listGroups = directoryManager.findAllGroup();
        for (Group group : listGroups) {
            groups.put(group.getId(), group.getName());
        }
        return groups;
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public ModelAndView informationGroup(@RequestParam(value = "id") Long id,@ModelAttribute Person person,Principal user){
        logger.info("information of group");
        person = directoryManager.findPerson(user,id);
        if(person != null) {
            return new ModelAndView("personInformation");
        }
        else{
            return new ModelAndView("personsList");
        }
    }

    @ModelAttribute("userInformation")
    public String userInformation(Principal principal){
        if(principal != null) {
            return principal.getName();
        }
        return "";
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(Principal user){
        if(user != null) {
            if (user.toString().contains("ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePerson(@RequestParam(value = "id") Long id, Principal user){
        var person = directoryManager.findPerson(user,id);
        if(person != null){
            person.getGroup().removePerson(person);
            directoryManager.removePerson(user,person.getId());
        }
        return "redirect:/person/list";
    }
}
