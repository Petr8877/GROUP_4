package votes_app.workers;

import java.util.Set;

public class DataValidator {

    private Filler filler = new Filler();

    public boolean checkCorrectChoiceGenres(String[]str){
        if(str == null){
            return false;
        }
        int lengthArr = str.length;
        Set<String> set = filler.fillSetWithWords(str);
        int sizeSet = set.size();
        return lengthArr == sizeSet && (sizeSet >= 3 && sizeSet <= 5);
    }

    public boolean checkCorrectUserInfo(String str){
        return str != null && !str.isEmpty();
    }
}
