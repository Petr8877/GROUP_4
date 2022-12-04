package votes_app.workers;

import java.util.*;

public class Filler {

    public Set<String> fillSetWithWords(String[] str) {
        Set<String>set = new HashSet<>();
        Collections.addAll(set, str);
        return set;
    }

    public void addNewValueToMap(Map<String, Integer>map, String str){
        Integer currentVal = map.get(str);
        map.put(str, currentVal+1);
    }

}
