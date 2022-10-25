package myapp.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import myapp.Starter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
@AutoConfigureMockMvc
public class TestGroupControler {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testGroupList() throws Exception {
        mvc.perform(get("/group/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groupList"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testGroupEdit() throws Exception {
        mvc.perform(get("/group/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groupForm"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testGroupInformation() throws Exception {
        mvc.perform(get("/group/information"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groupInformation"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testGroupDelete() throws Exception {
        mvc.perform(get("/group/delete"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testLimitationAccessUser() throws Exception {
        mvc.perform(get("/group/list"))
                .andDo(print())
                .andExpect(status().isOk());
        mvc.perform(get("/group/edit"))
                .andDo(print())
                .andExpect(status().isForbidden());
        mvc.perform(get("/group/information"))
                .andDo(print())
                .andExpect(status().isOk());
        mvc.perform(get("/group/delete"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
