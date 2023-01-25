package groupwork.dao.api;

import groupwork.entity.SingerEntity;

import java.util.List;

public interface ISingerDao {
    //исправить на entity
    List<SingerEntity> getSingerList();

    boolean isContain(int id);

    void delete(SingerEntity singerEntity);//int id

    void create(SingerEntity singerEntity);//String name

    void update(int id, SingerEntity singerEntity);
    SingerEntity get(int id);//int id
}
