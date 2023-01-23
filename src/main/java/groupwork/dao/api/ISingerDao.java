package groupwork.dao.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerDao {

    List<SingerDTO> getSingerList();

    boolean isContain(int id);

    void delete(int id);

    void create(String name);

    void update(int id, SingerDTO singerDTO);
    String get(Integer id);
}
