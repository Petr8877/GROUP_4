package by.it_course.groupwork.dao2.api;



import by.it_course.groupwork.dto.Voice;

import java.util.List;

public interface IVoiceDao {

    List<Voice> getVoiceList();

    void save(Voice voice);
}
