package myapp.web;

import myapp.dao.IDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/password-forgot")
public class PasswordForgotControler {

    @Autowired
    IDirectoryManager directoryManager;

    @Autowired
    PasswordForgotValidator passwordForgotValidator;


    @PostConstruct
    public void init(){}

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView forgotPassword(){
        return new ModelAndView("forgotPassword","message","");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView forgotPassword(@RequestParam(value = "username", required = false) String username){
        System.err.println("value = " + username);
        return new ModelAndView("forgotPassword","message",passwordForgotValidator.validate(username));
    }
}
