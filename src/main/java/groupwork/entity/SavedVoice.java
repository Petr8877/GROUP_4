package groupwork.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "votes")
public class SavedVoice {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vote_artists",
            joinColumns =
            @JoinColumn(name = "voice_id"),
            inverseJoinColumns =
            @JoinColumn(nullable = false)
    )
    private SingerEntity singer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vote_genre",
            joinColumns =
            @JoinColumn(name = "voice_id"),
            inverseJoinColumns =
            @JoinColumn(nullable = false)
    )
    private List<GenreEntity> genres = new ArrayList<>();

    private String message;
    private String email;
    private LocalDateTime creationTime;

    public SavedVoice() {
    }

    public SavedVoice(SingerEntity singer, List<GenreEntity> genres, String message, String email, LocalDateTime creationTime) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.creationTime = creationTime;
    }

    public SingerEntity getSinger() {
        return singer;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
