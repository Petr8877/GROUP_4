package groupwork.service.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerDTO> get();

}
