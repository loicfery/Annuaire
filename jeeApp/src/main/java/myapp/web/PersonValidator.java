package myapp.web;

import myapp.dao.IDirectoryDao;
import myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class PersonValidator implements Validator {

    @Autowired
    private IDirectoryDao dao;

    private static final String regexMail = "^(.+)@(.+).(.+)$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","person.first.name");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "person.last.name");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mail","person.mail");

        if(dao.findPersonByMail(person.getMail()).size() > 0){
            errors.rejectValue("mail","person.mail.used");
        }

        if(person.getGroup().getName().equals("")){
            errors.rejectValue("group","person.group");
        }

        if(!Pattern.compile(regexMail).matcher(person.getMail()).matches()){
            errors.rejectValue("mail","person.mail.form");
        }

        String birthday = person.getBirthday();
        if(birthday != null){
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                if(new Date().compareTo(date) < 0 ){
                    errors.rejectValue("birthday","person.birthday");
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        else{
            errors.rejectValue("birthday","person.birthday");
        }
    }

}
