package myapp.web;

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
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupControler {

    @Autowired
    IDirectoryManager directoryManager;

    @Autowired
    GroupValidator groupValidator;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init(){}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGroup(){
        logger.info("List of groups");
        return new ModelAndView("groupList");
    }

    @ModelAttribute("groups")
    List<Group> groups(){
        logger.info("Making list of groups");
        return directoryManager.findAllGroup();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView newGroup(@ModelAttribute Group group){
        return new ModelAndView("groupForm");
    }

    @ModelAttribute
    public Group newGroup(@RequestParam(value = "id", required = false) Long id,Principal user){
        if(id != null){
            logger.info("find group " + id);
            var group = directoryManager.findGroup(user,id);
            if(group != null) {
                return group;
            }
        }
        Group group = new Group();
        group.setName("");
        return group;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView saveGroup(@ModelAttribute("group") @Valid Group group, BindingResult result,Principal user){
        groupValidator.validate(group,result);
        if(result.hasErrors()){
            return new ModelAndView("groupForm");
        }
        directoryManager.saveGroup(user,group);
        return new ModelAndView("groupList");
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public ModelAndView informationGroup(@RequestParam(value = "id") Long id,@ModelAttribute Group group,Principal user){
        logger.info("information of group");
        group = directoryManager.findGroup(user,id);
        if(group != null) {
            return new ModelAndView("groupInformation");
        }
        else{
            return new ModelAndView("groupList");
        }
    }

    @RequestMapping("/find")
    public ModelAndView findGroups(String name,Principal user) {
        final var result = directoryManager.findGroupsByName(user,"%" + name + "%");
        return new ModelAndView("groupList", "groups", result);
    }

    @RequestMapping(value = "/information/find", method = RequestMethod.POST)
    public ModelAndView findPersons(@RequestParam(value = "id") Long id,@ModelAttribute String personName,Principal user) {
        var group = directoryManager.findGroup(user,id);
        if(group != null){
            final var result = directoryManager.findPersonInGroupByName(user,group.getName(),"%" + personName + "%");
            return new ModelAndView("groupList", "persons", result);
        }
        return new ModelAndView("groupList");
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
        var group = directoryManager.findGroup(user,id);
        if(group != null){
            for(Person p : group.getPersons()){
                p.setGroup(null);
            }
            directoryManager.removeGroup(user,group.getId());
        }
        return "redirect:/group/list";
    }
}
