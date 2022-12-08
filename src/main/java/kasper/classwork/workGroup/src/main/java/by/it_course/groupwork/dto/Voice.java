package by.it_course.groupwork.dto;

public class Voice {
    private String singer;
    private String[] genre;
    private ShortAboutUser aboutUser;

    public Voice(String singer, String[] genre, String message) {
        this.singer = singer;
        this.genre = genre;
        this.aboutUser = new ShortAboutUser(message);
    }

    public String getSinger() {
        return singer;
    }

    public String[] getGenre() {
        return genre;
    }

    public ShortAboutUser getAboutUser() {
        return aboutUser;
    }
}
