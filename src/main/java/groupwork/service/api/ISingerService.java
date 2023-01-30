package groupwork.service.api;

import groupwork.dto.SingerDTO;
import groupwork.entity.SingerEntity;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerDTO> get();

    void delete(SingerDTO singerDTO);

    void create(SingerDTO singerDTO);

    void update(int id, SingerDTO singerDTO);
    SingerDTO get(int id);

}
