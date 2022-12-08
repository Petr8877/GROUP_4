package by.it_course.groupwork.dao2;



import by.it_course.groupwork.dao2.api.IVoiceDao;
import by.it_course.groupwork.dto.Voice;

import java.util.ArrayList;
import java.util.List;

public class VoiceDao implements IVoiceDao {

    private List<Voice> voices = new ArrayList<>();

    @Override
    public List<Voice> getVoiceList() {
        return voices;
    }

    @Override
    public void save(Voice voice) {
        voices.add(voice);
    }
}
