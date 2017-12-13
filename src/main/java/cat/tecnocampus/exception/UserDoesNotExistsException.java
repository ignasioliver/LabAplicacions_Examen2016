package cat.tecnocampus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by roure on 12/12/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="username not found")
public class UserDoesNotExistsException extends RuntimeException {

    public String username;

    public UserDoesNotExistsException(String message) {
        super(message);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
