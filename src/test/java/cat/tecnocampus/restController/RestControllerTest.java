package cat.tecnocampus.restController;


import cat.tecnocampus.domain.Station;
import cat.tecnocampus.domain.User;
import cat.tecnocampus.domainController.FgcController;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FgcController mockFgcController;

    @Test
    public void testListStations() throws Exception {
        List<Station> stations = IntStream.range(0, 17)
                .mapToObj(i -> {Station s = new Station(); s.setNom("estacio " + i);return s;})
                .collect(Collectors.toList());
        when(mockFgcController.getStationsFromRepository()).thenReturn(stations);

        this.mockMvc.perform(get("/api/stations"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("estacio 1")));
    }

    @Test
    public void testListUsers() throws Exception {
        List<User> users = IntStream.range(0, 2)
                .mapToObj(i -> {
                    User u = new User(); u.setName("messi " +i); u.setPassword("password " + i);return u;})
                .collect(Collectors.toList());
        when(mockFgcController.getUsers()).thenReturn(users);

        Matcher<String> matcher = not(containsString("password"));

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("messi 1")))
                .andExpect(content().string(matcher));
    }

}

