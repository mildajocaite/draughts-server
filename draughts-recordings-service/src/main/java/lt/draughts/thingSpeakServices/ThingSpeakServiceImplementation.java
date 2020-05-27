package lt.draughts.thingSpeakServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import lt.draughts.model.ThingSpeakFeed;
import lt.draughts.model.ThingSpeakResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class ThingSpeakServiceImplementation implements ThingSpeakService {

    private String thingSpeakURL = "https://api.thingspeak.com/channels/";
    private String thingSpeakAPIKey = "Y91NR8ZXP5PK5P3I";

    @Override
    public List<ThingSpeakFeed> getRecordFromChannel(String channelCode, Date dateFrom) {
        try {
            String startDate = dateFrom.toInstant().toString();
            String url = thingSpeakURL + channelCode + "/feeds.json?api_key="
                    + thingSpeakAPIKey
                    + "&start=" + startDate
                    + "&end=" + Instant.now().toString();
            GetRequest request = Unirest.get(url);
            HttpResponse<JsonNode> response = request.asJson();
            if (response.getStatus() != 200) {
                System.out.println("Cannot read data from channel. Status code" + response.getStatus());
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ThingSpeakResponse thingspeakResponse = objectMapper.readValue(response.getBody().toString(), ThingSpeakResponse.class);
            return thingspeakResponse.getFeeds();
        } catch (Exception e) {
            System.out.println("Cannot read data from channel. " + e.toString());
        }
        return null;
    }
}
