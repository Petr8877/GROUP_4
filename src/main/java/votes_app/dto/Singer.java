package votes_app.dto;

import java.util.HashMap;
import java.util.Map;

public class Singer {
    private Map<String, Integer> singer = new HashMap<>();

    {
        singer.put("Sting", 0);
        singer.put("Scorpions", 0);
        singer.put("Madonna", 0);
        singer.put("GreenDay", 0);
    }

    public Map<String, Integer> getSinger() {
        return singer;
    }
}
