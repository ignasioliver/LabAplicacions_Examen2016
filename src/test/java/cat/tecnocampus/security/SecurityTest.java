package cat.tecnocampus.security;

import cat.tecnocampus.domainController.FgcController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FgcController mockFgcController;

    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void registeredUserStations() throws Exception {
        mockMvc.perform(get("/stations"))
                .andExpect(status().isOk());
    }

    @Test
    public void unregisteredUserStations() throws Exception {
        mockMvc.perform(get("/stations"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void registeredUserFavoriteJourney() throws Exception {
        mockMvc.perform(get("/user/favoriteJourney"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/user/favoriteJourney"))
                .andExpect(status().isOk());
    }

    @Test
    public void unregisteredUserFavoriteJourney() throws Exception {
        mockMvc.perform(get("/user/favoriteJourney"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        mockMvc.perform(post("/user/favoriteJourney"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username="ronaldo", roles={"USER","ADMIN"})
    public void registeredUserUsersAdmin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void registeredUserUsersNoAdmin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void unregisteredUserUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

}
