package myapp.web;

import com.sun.security.auth.UserPrincipal;
import myapp.dao.IDirectoryManager;
import myapp.model.Group;
import myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexControler {

    @Autowired
    IDirectoryManager directoryManager;

    @Autowired
    PersonValidator personValidator;

    @Value("${application.message:Please log in to access}")
    private String message;


    @RequestMapping("")
    public ModelAndView index(Principal principal) {
        directoryManager.login(principal);
        return new ModelAndView("index", "message", message);
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(Principal user){
        if(directoryManager.getRoles(user) != null) {
            for (Iterator<? extends GrantedAuthority> it = directoryManager.getRoles(user).iterator(); it.hasNext(); ) {
                if (it.next().toString().equals("ADMIN")) {
                    return true;
                }
            }
            return false;
        }
        else{
            return false;
        }
    }

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public ModelAndView login(Principal principal){
        if(principal == null) {
            return new ModelAndView("login");
        }
        else{
            return new ModelAndView("index");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            directoryManager.logout(principal);
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute Person person){
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPerson(@ModelAttribute @Valid Person person, BindingResult result){
        personValidator.validate(person,result);
        if(result.hasErrors()){
            return new ModelAndView("register");
        }
        UserPrincipal user = new UserPrincipal(person.getMail());
        var group = directoryManager.findGroup(user,Long.parseLong(person.getGroup().getName()));
        if(group != null) {
            group.addPerson(person);
            directoryManager.savePerson(user,person);
            directoryManager.addUser(person);
        }
        return new ModelAndView("login");
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

    @ModelAttribute("username")
    public String getUsername(Principal principal){
        if(principal != null){
            return principal.getName();
        }
        return "";
    }
}
