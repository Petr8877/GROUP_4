package by.it_course.groupwork.dao;

import by.it_course.groupwork.dao2.VotingDao;
import by.it_course.groupwork.dao2.api.IVotingDao;


public class VotingDaoSingleton {
    private volatile static VotingDao instance;

    private VotingDaoSingleton() {
    }

    public static IVotingDao getInstance() {
        if (instance == null) {
            synchronized (VotingDaoSingleton.class) {
                if (instance == null) {
                    instance = new VotingDao();
                }
            }
        }
        return instance;
    }
}
