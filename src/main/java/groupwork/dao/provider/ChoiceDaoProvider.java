package groupwork.dao.provider;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;

import groupwork.dao.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider {
    private static volatile ChoiceDaoProvider instance;
    private static boolean isDB = true;
    private IDaoProvider iDaoProvider;

    private ChoiceDaoProvider() {
        if (isDB) {
            iDaoProvider = new DaoDBProvider();
        }
    }

    @Override
    public IGenreDao genreDao() {
        return iDaoProvider.genreDao();
    }

    @Override
    public ISingerDao singerDao() {
        return iDaoProvider.singerDao();
    }

    @Override
    public IVotingDao voteDao() {
        return iDaoProvider.voteDao();
    }

    public static IDaoProvider getInstance() {
        if (instance == null) {
            synchronized (ChoiceDaoProvider.class) {
                if (instance == null) {
                    instance = new ChoiceDaoProvider();
                }
            }
        }
        return instance;
    }
}
