package cat.tecnocampus.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by roure on 18/11/2016.
 */
public class DayTimeStart {

    private long id;

    /*
    TODO 4.0 (Regular expression)
        * Explain below (in this comment) the meaning of the following regrep experssion: "^([0,1][0-9]|2[0-3]):[0-5][0-9]$"
        HINT: is a pattern for a time of the day "hh:mm"

        * YOUR ANSWER:
        * Es un patro per les hores, si val 1 com a maxim el segon valor pot valdre 9. Si el primer valor es un
        * 2 el segon valor pot ser com a maxim un 3.
        * El primer valor de les hores pot valdre com a maxim 5 i el segon com a maxim 9 (59 minuts).

    */

    /*
    TODO 4.1 (Follow pattern) ensure that the timeStart property follows the above regrep expression (the one in 4.0)
        HINT
        * add an annotation */

    @Pattern(regexp = "^([0,1][0-9]|2[0-3]):[0-5][0-9]$")
    @NotNull(message = "Start time cannot be null")
    private String timeStart;

    private String dayOfWeek;

    public DayTimeStart() {}

    public DayTimeStart(String day, String time) {
        dayOfWeek = day;
        timeStart = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayTimeStart that = (DayTimeStart) o;

        if (timeStart != null ? !timeStart.equals(that.timeStart) : that.timeStart != null) return false;
        return dayOfWeek != null ? dayOfWeek.equals(that.dayOfWeek) : that.dayOfWeek == null;
    }

    @Override
    public int hashCode() {
        int result = timeStart != null ? timeStart.hashCode() : 0;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return result;
    }
}
