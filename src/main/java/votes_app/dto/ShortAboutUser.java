package votes_app.dto;

import java.time.LocalDateTime;

public class ShortAboutUser {
    private String aboutUser;
    private LocalDateTime creationTime;

    public ShortAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
        this.creationTime = LocalDateTime.now();
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public String toString() {
        return "aboutUser = " + aboutUser + ", creationTime =" + creationTime;
    }
}
