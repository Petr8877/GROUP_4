package groupwork.dao.api;

import groupwork.dto.GenreDTO;
import groupwork.entity.Entity;

import java.util.List;

public interface BaseDao <K,T extends Entity>{
    boolean insert(T t);
    T update( T t);
    boolean delete(T t);
    List<T> getAll();
    boolean isContain(K id);
}
