package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToDisplay {
    private String lastName;
    private String firstName;
    private String email;

    public UserToDisplay(String email, String lastName, String firstName) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setEmail(email);
    }
}
