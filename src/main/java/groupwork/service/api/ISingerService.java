package groupwork.service.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerDTO> get();

    void delete(int id);

    void create(String name);

    void update(int id, SingerDTO singerDTO);

}
