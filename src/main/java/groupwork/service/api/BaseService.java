package groupwork.service.api;

import groupwork.entity.Entity;

import java.util.List;

public interface BaseService <K,T extends Entity>{
    boolean insert(T t);
    T update(T t);
    boolean delete(T t);
    List<T> getAll();

    boolean isContain(K id);

}
