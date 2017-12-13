package cat.tecnocampus.domainController;

import cat.tecnocampus.domain.FavoriteJourney;
import cat.tecnocampus.domain.Station;
import cat.tecnocampus.domain.User;
import cat.tecnocampus.exception.SameOriginDestinationException;
import cat.tecnocampus.exception.UserDoesNotExistsException;
import cat.tecnocampus.repositories.FavoriteJourneyRepository;
import cat.tecnocampus.repositories.StationRepository;
import cat.tecnocampus.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roure on 07/12/2016.
 */
@Service
public class FgcController {
    private StationRepository stationRepository;
    private UserRepository userRepository;
    private FavoriteJourneyRepository favoriteJourneyRepository;

    public FgcController(StationRepository stationRepository, UserRepository userRepository,
                         FavoriteJourneyRepository favoriteJourneyRepository) {
        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
        this.favoriteJourneyRepository = favoriteJourneyRepository;
    }

    public List<Station> getStationsFromRepository() {
        return stationRepository.findAll();
    }

    public void saveStations(List<Station> stations) {
        stationRepository.saveStations(stations);
    }

    public User getUser(String username) {
        User user = userRepository.findOneUser(username);

        user.setFavoriteJourneyList(favoriteJourneyRepository.findFavoriteJourneys(username));

        return user;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.getUsers();
        users.forEach(u -> u.setFavoriteJourneyList(favoriteJourneyRepository.findFavoriteJourneys(u.getUsername())));

        return users;
    }

    public boolean existsUser (String username) {
        return userRepository.existsUser(username);
    }

    public void addUserFavoriteJourney(String username, FavoriteJourney favoriteJourney) {
        if (favoriteJourney.getJourney().sameOriginDestination()) {
            throw new SameOriginDestinationException();
        }
        if (existsUser(username)) {
            favoriteJourneyRepository.saveFavoriteJourney(favoriteJourney, username);
        }
        else {
            UserDoesNotExistsException e = new UserDoesNotExistsException("Non existing resource");
            e.setUsername(username);
            throw e;
        }
    }

    public List<FavoriteJourney> getFavoriteJourneys(String username) {
        if (!existsUser(username)) {
            UserDoesNotExistsException e = new UserDoesNotExistsException("Non existing resource");
            e.setUsername(username);
            throw e;
        }

        return favoriteJourneyRepository.findFavoriteJourneys(username);
    }
}
