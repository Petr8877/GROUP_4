package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "app.genres")
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    private String name;


    public GenreEntity() {
    }

    public GenreEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreEntity(int id) {
        this.id = id;
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}