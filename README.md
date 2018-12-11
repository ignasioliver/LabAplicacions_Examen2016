# Explicacions de les solucions
## mvnw.
### TODO classpath?
No crec que entri. Repeteix el mateix que el del bloc anterior fet per Cygwin per Migwn.

---

## SEGURETAT.
### TODO 1 (Security): modify the matchers so that...
```java
.antMatchers("/ruta/**").permitAll() // ** sivolem tot a partir d'alla
.antMatchers("/ruta").hasRole("ADMIN") // ADMIN o el que sigui
.antMatchers("/h2-console/**").authenticated // per qualsevol login
.formLogin // Per defecte redirigeix al recurs que s'intentava accedir. Un //.successForwardUrl("/welcome") dins seu forca una redireccio a welcome.
```

---

## REGULAR EXPRESSION.
### TODO 4.0 (Regular expression)...
Es un patro per les hores, si val 1 com a maxim el segon valor pot valdre 9. Si el primer valor es un 2 el segon valor pot ser com a maxim un 3. El primer valor de les hores pot valdre com a maxim 5 i el segon com a maxim 9 (59 minuts).

---

## FOLLOW PATTERN (per timeStart).
### TODO 4.1 (Follow pattern) ensure that the timeStart property follows the above regrep expression (the one in 4.0)...

```java

@Pattern(regexp = "^([0,1][0-9]|2[0-3]):[0-5][0-9]$")
```

---

## SQL TEMPLATES.
### TODO 2 (SqlTemplate): this method should insert into the BBDD all the DayTimeStart objects in the given list to the given favoriteJourney...

En l'examen
```java
private int[] saveDayTimeStart(List<DayTimeStart> start, long favoriteJourneyId) {
  return jdbcTemplate.batchUpdate(TOINSERT, new BatchPreparedStatementSetter() {

  public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
    preparedStatement.setString(1, start.get(i).getTimeStart());
    preparedStatement.setString(2, start.get(i).getDayOfWeek());
    preparedStatement.setLong(3, favoriteJourneyId);
    }

    @Override
    public int getBatchSize() {
      return start.size();
    }
  });
}
```
Exemple cas Sergi:
```java
public int[] insertClassrooms(List<Classroom> classroom) {
    return jdbcTemplate.batchUpdate(INSERT_SET_OF_CLASSROOMS, new BatchPreparedStatementSetter() {

    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        preparedStatement.setString(1, classroom.get(i).getName());
        preparedStatement.setInt(2, classroom.get(i).getCapacity());
        preparedStatement.setString(3, classroom.get(i).getOrientation());
        preparedStatement.setBoolean(4, classroom.get(i).isPlugs());
        }

        @Override
        public int getBatchSize() {
            return classroom.size();
        }
    });
}
```

---

## REST API (JSON), NO FUNCIONA.
### TODO 6 (REST api) code a rest controller with two get methods that return Json data:

Afegir llibreria **Maven:org.json..**.
Importar:
```java
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
```
Inserir:
```java
@GetMapping(value = "api/stations", produces = MediaType.APPLICATION_JSON_VALUE)
public JSONArray UserJSONArray(){
    JSONArray ja = new JSONArray();
    for (User element : fgcController.getUsers()){
        ja.put(element);
    }
    return ja;
}

public JSONArray StationJSONArray(){
    JSONArray ja = new JSONArray();
    for (Station element : fgcController.getStationsFromRepository()){
        ja.put(element);
    }
    return ja;
}
```
Al WebController:
```java
private MyRestController API; /* + implementacio del codi*/
```

---

## EXCEPTIONS.
### TODO 5 (Exceptions) Make this class cope with Exceptions thrown/caused by the WebController...
User... => la classe que et demani
```java
@ExceptionHandler(UserDoesNotExistsException.class)
```

---

## LEGAL FORM OBJECTS.
### TODO 4.2 (Legal form objects) Make sure all "startTime"s of the "favoriteJourney" are correct in the sense that they follow the patterns of the previous TO.DO 4.1
Afegir @Valid where REGEX/ was applied (or if it was NOT NULL, or whatever format it was) - in same classs it is asked
E.g. changed on:
```java
    public String postAddFavoriteJourney(Principal principal, @Valid FavoriteJourney favoriteJourney, Errors errors, Model model) {
```

---

## Thymeleaf.
### TODO 3 (Thymeleaf): In the following selects (origin and destination) station names should be given to the user to choose from
Anar amb compte amb els retorns de rutes, que tinguin el mateix nom que el que busquem:
Fer un th:each amb el favoriteJourney. Despres modificar el webController:
```java
<label th:class="${#lists.size(myErrors)>0}? 'error'">Origin:</label>
    <select name="journey.origin.nom">
        <div th:each="favoriteJourney : ${stationList}">
            <option th:text="${favoriteJourney.nom}">Station name (nom)
            </option>

        </div>

    </select>
    <label th:class="${#lists.size(myErrors)>0}? 'error'">Destination:</label>
    <select name="journey.destination.nom">
        <div th:each="favoriteJourney : ${stationList}">
            <option th:text="${favoriteJourney.nom}">Station name (nom)
            </option>

        </div>
    </select>
```
Modificacio del webController; afegir:
```java
model.addAttribute("stationList", fgcController.getStationsFromRepository());
```
a la funcio getAddFavoriteJourney. Queda aixi:
```java
@GetMapping("/user/favoriteJourney")
    public String getAddFavoriteJourney(Principal principal, Model model) {
        fillModelForNewFavoriteJourney(model, principal, new FavoriteJourney());
        fillModelForNewFavoriteJourney(model, principal, new FavoriteJourney());
        model.addAttribute("stationList", fgcController.getStationsFromRepository());

        return "newFavoriteJourney";
    }
```

### TODO 7 (Thymeleaf): It's not mandatory. If you do this Todo it will give you an extra-point.
Havent implementat la resta de TODOs, senzillament afegir @Valid a la classe FavoriteTimeJourney, de manera que quedi:
```java
@Valid
private List<DayTimeStart> startList;
```
