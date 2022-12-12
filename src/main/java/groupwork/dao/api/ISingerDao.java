package groupwork.dao.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerDao {

    List<SingerDTO> getSingerList();


    boolean isContain(int id);
}
