package groupwork.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "voice")
public class SavedVoice {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "voice_singer",
            joinColumns =
            @JoinColumn(name = "voice_id"),
            inverseJoinColumns =
            @JoinColumn(nullable = false)
    )
    private Singer singer;

    //    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(
//            joinColumns = @JoinColumn(name = "voice_id")
//    )
//    @Column(name = "genre")
//    private List<Genre> genres = new ArrayList<>();
    //worked
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "voice_genre",
//            joinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "voice_genre",
            joinColumns =
            @JoinColumn(name = "voice_id"),
            inverseJoinColumns =
            @JoinColumn(nullable = false)
    )
    private List<Genre> genres = new ArrayList<>();

    private String message;
    private String email;
    private LocalDateTime creationTime;

    public SavedVoice() {
    }

    public SavedVoice(Singer singer, List<Genre> genres, String message, String email, LocalDateTime creationTime) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.creationTime = creationTime;
    }

    public Singer getSinger() {
        return singer;
    }


    public List<Genre> getGenres() {
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
