package groupwork.service.fabrics;

import groupwork.dao.provider.ChoiceDaoProvider;
import groupwork.service.VoteService;
import groupwork.service.api.IVotesService;


public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static IVotesService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(ChoiceDaoProvider.getInstance().voteDao(),
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance(),
                            MailServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
