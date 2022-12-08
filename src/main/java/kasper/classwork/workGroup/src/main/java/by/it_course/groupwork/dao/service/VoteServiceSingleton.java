package by.it_course.groupwork.dao.service;

import by.it_course.groupwork.dao.VoiceDaoSingleton;
import by.it_course.groupwork.service.VoteService;


public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static VoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(VoiceDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
