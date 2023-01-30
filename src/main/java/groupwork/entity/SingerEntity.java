package groupwork.entity;

import groupwork.dto.SavedVoiceEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app.artists")
public class SingerEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    private String name;

    /*@OneToMany(mappedBy = "singer")
    private List<SavedVoiceEntity> voice = new ArrayList<>();*/

    public SingerEntity() {
    }

    public SingerEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SingerEntity(String name) {
        this.name = name;
    }

    public SingerEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
