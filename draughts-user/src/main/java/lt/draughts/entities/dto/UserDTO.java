package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull(message = "Email cannot be null.")
    @Email(message = "Wrong email format.")
    private String email;
    @NotNull(message = "FirstName cannot be null.")
    private String firstname;
    @NotNull(message = "LastName cannot be null.")
    private String lastname;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "Role cannot be null")
    private String role;
    private String boardName;
    private String boardCode;
    private UserDTO coach;
}