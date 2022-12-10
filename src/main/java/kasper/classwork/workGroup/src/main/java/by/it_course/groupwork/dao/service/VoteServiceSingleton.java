package by.it_course.groupwork.dao.service;

import by.it_course.groupwork.dao.VotingDaoSingleton;
import by.it_course.groupwork.service.VoteService;
import by.it_course.groupwork.service.api.IVotesService;


public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static IVotesService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(VotingDaoSingleton.getInstance(), SingersServiceSingleton.getInstance(), GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
