package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ThingSpeakChannel {
    private Date created_at;
    private Double latitude;
    private Double longitude;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private Integer id;
    private Integer last_entry_id;
    private String name;
    private Date updated_at;
}
