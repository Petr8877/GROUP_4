package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "app.artists")
public class SingerEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    private String name;

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
