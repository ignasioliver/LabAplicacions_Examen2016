package cat.tecnocampus.restController;

import cat.tecnocampus.domain.Station;
import cat.tecnocampus.domain.User;
import cat.tecnocampus.domainController.FgcController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 TODO 6 (REST api) code a rest controller with two get methods that return Json data:
    * api/stations returns a Json array with all the stations
    * api/users returns a Json array with all the users. The password of the users must not be shown
 HINT
    * Use fgcController.getStationsFromRepository() to get the station's list
    * Use fgcController.getUsers() to get the user's list
    * In order to hide the user's password you'll need to add an annotation somewhere else
 */

public class MyRestController {
    private FgcController fgcController;

    public MyRestController(FgcController fgcController) {
        this.fgcController = fgcController;
    }

}
