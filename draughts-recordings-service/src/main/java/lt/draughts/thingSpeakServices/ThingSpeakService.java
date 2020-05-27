package lt.draughts.thingSpeakServices;

import lt.draughts.model.ThingSpeakFeed;

import java.util.Date;
import java.util.List;

public interface ThingSpeakService {
    List<ThingSpeakFeed> getRecordFromChannel(String channelCode, Date dateFrom);
}
