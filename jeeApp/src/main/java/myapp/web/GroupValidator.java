package myapp.web;

import myapp.dao.IDirectoryDao;
import myapp.model.Group;
import myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class GroupValidator implements Validator{

    @Autowired
    IDirectoryDao dao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Group group = (Group) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "group.name");
    }
}
