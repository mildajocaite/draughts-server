package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ThingSpeakResponse {
    private ThingSpeakChannel channel;
    private List<ThingSpeakFeed> feeds;
}
