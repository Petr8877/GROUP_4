package groupwork.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
//@Table(name = "singer")
public class Singer extends Entity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    private String name;

    @OneToMany(mappedBy = "singer")
    private List<SavedVoice> voice = new ArrayList<>();

    public Singer() {
    }

    public Singer(String name) {
        this.name = name;
    }

    public Singer(int id) {
        this.id = id;
    }

    public Singer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SavedVoice> getVoice() {
        return voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Singer singer = (Singer) o;

        if (id != singer.id) return false;
        return name != null ? name.equals(singer.name) : singer.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
