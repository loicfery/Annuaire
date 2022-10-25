package myapp.web;

import myapp.dao.IDirectoryDao;
import myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Service
public class PasswordForgotValidator implements  Validator{

    @Autowired
    private IDirectoryDao dao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public String validate(String username){
        if(username.isEmpty() || username.isBlank()){
            return "The mail is required !";
        }
        if(dao.findPersonByMail(username).size() == 0){
            return "The mail is incorrect !";
        }
        return "The mail has be send !";
    }
}
