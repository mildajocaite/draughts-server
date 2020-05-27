package lt.draughts.services;

import java.util.Date;
import java.util.List;

public interface RecordingService {
    List<String> getMoves(String boardCode, Date from);

    Date getStartPositionDate(String boardCode, Date dateFrom, int[][] position);

    Date getInitialPosition(String boardCode, Date dateFrom);
}
