package groupwork.dao.api;

import groupwork.entity.SavedVoice;

import java.util.List;

public interface IVotingDao {
    List<SavedVoice> getVoiceList();


    void save(SavedVoice voice);
}
