package lt.draughts.entities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersSortedByResolvedTasks {
    private String lastName;
    private String firstName;
    private String email;
    private String type;
    private long numberOfEasyTasks;
    private long numberOfMediumTasks;
    private long numberOfHardTasks;
    private long points;
    private int place;
}

