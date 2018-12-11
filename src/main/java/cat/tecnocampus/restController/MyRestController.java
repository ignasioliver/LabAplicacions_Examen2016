package cat.tecnocampus.restController;

import cat.tecnocampus.domain.Station;
import cat.tecnocampus.domain.User;
import cat.tecnocampus.domainController.FgcController;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
 TODO 6 (REST api) code a rest controller with two get methods that return Json data:
    * api/stations returns a Json array with all the stations
    * api/users returns a Json array with all the users. The password of the users must not be shown
 HINT
    * Use fgcController.getStationsFromRepository() to get the station's list
    * Use fgcController.getUsers() to get the user's list
    * In order to hide the user's password you'll need to add an annotation somewhere else
 */

@RestController
@RequestMapping("/API")
public class MyRestController {
    private FgcController fgcController;

    public MyRestController(FgcController fgcController) {

        this.fgcController = fgcController;

    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String UserJSONArray(){
        JSONArray ja = new JSONArray();
        for (User element : fgcController.getUsers()){
            ja.put(element);
        }
        return ja.toString();
    }

    @GetMapping(value = "/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    public String StationJSONArray(){
        JSONArray ja = new JSONArray();
        for (Station element : fgcController.getStationsFromRepository()){
            ja.put(element);
        }
        return ja.toString();
    }

}
