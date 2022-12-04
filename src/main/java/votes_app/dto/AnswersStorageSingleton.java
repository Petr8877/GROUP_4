package votes_app.dto;

import kasper.classwork.votes_app.workers.Filler;

import java.util.*;
import java.util.stream.Collectors;

public class AnswersStorageSingleton {

    private Map<String, Integer> votingSinger;
    private Map<String, Integer> votingGenre;
    private List<ShortAboutUser> userInfo;

    private Filler filler = new Filler();
    private Genre genres = new Genre();
    private Singer singers = new Singer();

    private static AnswersStorageSingleton votingResults = new AnswersStorageSingleton();

    private AnswersStorageSingleton(){
       votingSinger = singers.getSinger();
       votingGenre = genres.getGenres();
       userInfo = new ArrayList<>();
    }

    public static AnswersStorageSingleton getInstance() {
        return votingResults;
    }

    public void addVoiceSinger(String singer){
        filler.addNewValueToMap(votingSinger, singer);
    }

    public void addVoiceGenre(String[]genre){
        for (String s : genre) {
            filler.addNewValueToMap(votingGenre, s);
        }
    }

    public void addUserInfo(ShortAboutUser aboutUser){
        userInfo.add(aboutUser);
    }

    public Map<String, Integer> getVotingSinger() {
        return votingSinger;
    }

    public Map<String, Integer> getVotingGenre() {
        return votingGenre;
    }

    public List<ShortAboutUser> getUserInfo() {
        return userInfo;
    }

    public Map<String, Integer> getSortGenres() {
        return  votingGenre.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue
                        ,(v1, v2) -> v1, LinkedHashMap::new));
    }

    public Map<String, Integer> getSortSingers() {
        return votingSinger.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder(Integer::compare)))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue
                        ,(v1, v2) -> v1, LinkedHashMap::new));

    }

    public List<ShortAboutUser> getSortUserInfo() {
        List<ShortAboutUser> list = userInfo;
        list.sort((v1, v2) -> v2.getCreationTime().compareTo(v1.getCreationTime()));
        return list;
    }




}
