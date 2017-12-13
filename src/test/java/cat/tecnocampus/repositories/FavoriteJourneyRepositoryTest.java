package cat.tecnocampus.repositories;

import cat.tecnocampus.domain.DayTimeStart;
import cat.tecnocampus.domain.FavoriteJourney;
import cat.tecnocampus.domain.Journey;
import cat.tecnocampus.domain.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by roure on 13/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteJourneyRepositoryTest {

    @Autowired
    FavoriteJourneyRepository favoriteJourneyRepository;

    @Test
    public void storeFavoriteJourneyDayTime() throws Exception{
        FavoriteJourney favoriteJourney = createFavoriteJourney();
        final String user = "messi";

        favoriteJourneyRepository.saveFavoriteJourney(favoriteJourney, user);

        assertTrue("1:1 should be found", favoriteJourneyRepository.findFavoriteJourneys(user).stream()
                .anyMatch(f -> f.getStartList().stream().anyMatch(s -> s.getTimeStart().equalsIgnoreCase("1:1"))));
    }

    public static FavoriteJourney createFavoriteJourney() {
        FavoriteJourney favoriteJourney = new FavoriteJourney();
        Station origin, destination;
        Journey journey;
        List<DayTimeStart> starts;

        origin = new Station();
        origin.setNom("Lleida-Pirineus");
        destination = new Station();
        destination.setNom("Tremp");

        journey = new Journey(origin, destination);

        starts = IntStream.range(0, 3)
                .mapToObj(i -> {
                    DayTimeStart d = new DayTimeStart("Monday", i + ":" + i); return d;})
                .collect(Collectors.toList());

        favoriteJourney.setJourney(journey);
        favoriteJourney.setStartList(starts);

        return favoriteJourney;
    }
}
