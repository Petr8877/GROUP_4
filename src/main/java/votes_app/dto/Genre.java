package votes_app.dto;

import java.util.HashMap;
import java.util.Map;

public class Genre {

    private Map<String, Integer> genres = new HashMap<>();

    {
        genres.put("pop", 0);
        genres.put("rock", 0);
        genres.put("alternative", 0);
        genres.put("folk", 0);
        genres.put("bluez", 0);
        genres.put("pop-rock", 0);
        genres.put("jazz", 0);
        genres.put("classic", 0);
        genres.put("electro", 0);
        genres.put("cantry", 0);
    }

    public Map<String, Integer> getGenres() {
        return genres;
    }


}
