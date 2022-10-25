package myapp.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import myapp.Starter;
import myapp.model.Group;
import myapp.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
@AutoConfigureMockMvc
public class TestPersonControler {

    @Autowired
    private MockMvc mvc;

    private Person person;
    private Group group;

    @BeforeEach
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void setUp() throws Exception{
        group = new Group("group 1");
        group.setId(1L);
        RequestBuilder request = post("/group/edit").flashAttr("group",group).with(csrf());
        mvc.perform(request).andDo(print()).andExpect(status().isOk());

        person = new Person("loic","fery","password");
        person.setMail("mail@mail.com");
        person.setBirthday("2022-04-01");
        person.setGroup(new Group(group.getId().toString()));
        request = post("/person/edit").flashAttr("person",person).with(csrf());
        mvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testPersonsList() throws Exception{
        mvc.perform(get("/person/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("personsList"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testPersonEdit() throws Exception {
        mvc.perform(get("/person/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("personForm"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testPersonInformation() throws Exception {
        mvc.perform(get("/person/information?id={id}",person.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("personInformation"));

    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testPersonDelete() throws Exception{
        mvc.perform(get("/person/delete"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testLimitationAccessUser() throws Exception {
        mvc.perform(get("/person/list"))
                .andDo(print())
                .andExpect(status().isOk());
        mvc.perform(get("/person/edit"))
                .andDo(print())
                .andExpect(status().isOk());
        mvc.perform(get("/person/information"))
                .andDo(print())
                .andExpect(status().isOk());
        mvc.perform(get("/person/delete"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
