package cat.tecnocampus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roure on 14/11/2016.
 */
public class User implements Serializable {
    private String username;

    private String name;
    private String secondName;

    private String email;

    @JsonIgnore
    private String password;

    public List<FavoriteJourney> favoriteJourneyList;


    public User() {
       favoriteJourneyList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FavoriteJourney> getFavoriteJourneyList() {
        return favoriteJourneyList;
    }

    public void setFavoriteJourneyList(List<FavoriteJourney> favoriteJourneyList) {
        this.favoriteJourneyList = favoriteJourneyList;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favoriteJourneyList=lazy" +
                '}';
    }
}
