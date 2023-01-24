package groupwork.dao.provider;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;

import groupwork.dao.provider.api.IDaoProvider;

import static groupwork.dao.provider.TypesOfMemory.*;

public class ChoiceDaoProvider implements IDaoProvider {
    private static volatile ChoiceDaoProvider instance;
    private static TypesOfMemory isDB = DATA_BASE;
    private IDaoProvider iDaoProvider;

    private ChoiceDaoProvider() {
        if (isDB == DATA_BASE) {
            iDaoProvider = new DaoDBProvider();
        } else if (isDB == PC_MEMORY){
            iDaoProvider = new DaoMemoryProvider();
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
