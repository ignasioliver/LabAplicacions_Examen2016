package cat.tecnocampus.webController;

import cat.tecnocampus.domain.DayTimeStart;
import cat.tecnocampus.domain.FavoriteJourney;
import cat.tecnocampus.domain.Journey;
import cat.tecnocampus.domain.Station;
import cat.tecnocampus.domainController.FgcController;
import cat.tecnocampus.exception.UserDoesNotExistsException;
import cat.tecnocampus.repositories.FavoriteJourneyRepositoryTest;
import cat.tecnocampus.security.SecurityTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FgcController mockFgcController;

    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void testListStations() throws Exception {
        List<Station> stations = IntStream.range(0, 17)
                .mapToObj(i -> {Station s = new Station(); s.setNom("estacio " + i);return s;})
                .collect(Collectors.toList());
        when(mockFgcController.getStationsFromRepository()).thenReturn(stations);

        this.mockMvc.perform(get("/user/favoriteJourney"))
                .andExpect(status().isOk())
                .andExpect(view().name("newFavoriteJourney"))
                .andExpect(content().string(containsString("estacio 1")));
    }

    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void testFavoriteJourneyErrors() throws Exception {
        this.mockMvc.perform(
                post("/user/favoriteJourney")
                        .param("journey.origin.nom", "origen")
                        .param("journey.destination.nom", "desti")
                        .param("startList[0].dayOfWeek", "Monday")
                        .param("startList[0].timeStart", "99:99")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("newFavoriteJourney"))
                .andExpect(model().attributeHasErrors("favoriteJourney"))
                .andExpect(content().string(containsString("Start time must be hh:mm")));
    }


    @Test
    @WithMockUser(username="messi", roles={"USER"})
    public void testFavoriteJourneyErrorsCorrect() throws Exception {
        this.mockMvc.perform(
                post("/user/favoriteJourney")
                        .param("journey.origin.nom", "origen")
                        .param("journey.destination.nom", "desti")
                        .param("startList[0].dayOfWeek", "Monday")
                        .param("startList[0].timeStart", "11:59")
        )
                .andExpect(redirectedUrl("/user/favoriteJourneys"))
                .andExpect(model().hasNoErrors());
    }
}
