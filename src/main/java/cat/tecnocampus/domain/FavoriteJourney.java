package cat.tecnocampus.domain;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roure on 14/11/2016.
 */
public class FavoriteJourney {

    private long id;

    private List<DayTimeStart> startList;

    private Journey journey;

    public FavoriteJourney() {
        startList = new ArrayList<>();
        startList.add(new DayTimeStart());
        journey = new Journey();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DayTimeStart> getStartList() {
        return startList;
    }

    public void setStartList(List<DayTimeStart> startList) {
        this.startList = startList;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @Override
    public String toString() {
        return "FavoriteJourney{" +
                "id=" + id +
                //", startList=" + startList +
                ", journey=" + journey +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteJourney that = (FavoriteJourney) o;

        if (startList != null ? !startList.equals(that.startList) : that.startList != null) return false;
        return journey != null ? journey.equals(that.journey) : that.journey == null;
    }

    @Override
    public int hashCode() {
        int result = startList != null ? startList.hashCode() : 0;
        result = 31 * result + (journey != null ? journey.hashCode() : 0);
        return result;
    }
}
