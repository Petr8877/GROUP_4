package groupwork.service.api;

import groupwork.dto.SingerDTO;
import groupwork.entity.SingerEntity;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerEntity> get();

    void delete(int id);

    void create(String name);

    void update(int id, SingerDTO singerDTO);
    String get(Integer id);

}
