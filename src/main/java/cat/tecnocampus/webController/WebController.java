package cat.tecnocampus.webController;

import cat.tecnocampus.domain.DayTimeStart;
import cat.tecnocampus.domain.FavoriteJourney;
import cat.tecnocampus.domainController.FgcController;
import cat.tecnocampus.exception.UserDoesNotExistsException;
import cat.tecnocampus.restController.MyRestController;
import org.json.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;

/**
 * Created by roure on 14/11/2016.
 */
@Controller

public class WebController {

    private FgcController fgcController;

    /* REFERENT A TODO 6*/
    private MyRestController API;

    public WebController(FgcController fgcController, MyRestController API) {
        this.fgcController = fgcController;
        this.API = API;
    }


    @GetMapping("/stations")
    public String getStations(Model model) {

        model.addAttribute("stationList", fgcController.getStationsFromRepository());

        return "stations";
    }

    @GetMapping("/user/favoriteJourney")
    public String getAddFavoriteJourney(Principal principal, Model model) {

        fillModelForNewFavoriteJourney(model, principal, new FavoriteJourney());
        fillModelForNewFavoriteJourney(model, principal, new FavoriteJourney());
        model.addAttribute("stationList", fgcController.getStationsFromRepository());

        return "newFavoriteJourney";
    }

    /*
    TODO 4.2 (Legal form objects) Make sure all "startTime"s of the "favoriteJourney" are correct in the sense that they
    follow the patterns of the previous TO.DO 4.1
        HINT
            * add an annotation in the parameter of the following method
            * You'll need to add the same annotation in another file/class
     */
    @PostMapping("/user/favoriteJourney")
    public String postAddFavoriteJourney(Principal principal,@Valid FavoriteJourney favoriteJourney, Errors errors, Model model) {

        if (errors.hasErrors()) {
            fillModelForNewFavoriteJourney(model, principal, favoriteJourney);
            System.out.println(errors.getAllErrors());
            return "newFavoriteJourney";
        }

        fgcController.addUserFavoriteJourney(principal.getName(), favoriteJourney);
        model.addAttribute("favoriteJourney", favoriteJourney);

        return "redirect:/user/favoriteJourneys";
    }

    private void fillModelForNewFavoriteJourney(Model model, Principal principal, @Valid FavoriteJourney favoriteJourney) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("stationList", fgcController.getStationsFromRepository());
        model.addAttribute("favoriteJourney", favoriteJourney);
        model.addAttribute("myErrors", new ArrayList<String>());
    }

    @GetMapping("/user/favoriteJourneys")
    public String getFavoriteJourneys(Principal principal, Model model) {
        String username = principal.getName();

        System.out.println("web controller: going to show favorite journeys");

        model.addAttribute("username", username);
        model.addAttribute("favoriteJourneys", fgcController.getFavoriteJourneys(username));
        return "favoriteJourneys";
    }

    @GetMapping("/byebye")
    public String byebye() {

        return "byebye";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", fgcController.getUsers());

        return "users";
    }

    @PostMapping("/welcome")
    public String welcome(Principal principal, Model model) {
        model.addAttribute("tuser", fgcController.getUser(principal.getName()));
        return "welcome";
    }

    @GetMapping("/users/{username}")
    public String user(@PathVariable String username, Model model) {
        model.addAttribute("tuser", fgcController.getUser(username));

        return "user";
    }



    /* REFERENT A TODO 6 */
/*    @GetMapping(value = "/api/stations/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONArray station() {
        return API.StationJSONArray();
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONArray user() {
        return API.UserJSONArray();
    }*/


}
