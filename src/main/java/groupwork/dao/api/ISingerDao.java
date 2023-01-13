package groupwork.dao.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerDao {

    void add(String name);

    void delete(String name);

    void update(long id, String name);
    List<SingerDTO> getList();


    boolean isContain(int id);
}
