package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.VoiceDao;


public class VoiceDaoSingleton {
    private volatile static VoiceDao instance;

    public static VoiceDao getInstance() {
        if (instance == null) {
            synchronized (VoiceDaoSingleton.class) {
                if (instance == null) {
                    instance = new VoiceDao();
                }
            }
        }
        return instance;
    }
}
