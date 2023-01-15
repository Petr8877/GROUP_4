package groupwork.dao.fabrics;

import groupwork.dao.VotingDao;
import groupwork.dao.VotingDaoDB;
import groupwork.dao.api.IVotingDao;


public class VotingDaoSingleton {
    private volatile static IVotingDao instance;

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
