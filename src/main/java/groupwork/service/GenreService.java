package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.entity.GenreEntity;
import groupwork.entity.SingerEntity;
import groupwork.service.api.IGenreService;


import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean check(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Введите id жанра");
        }
        return this.dao.isContain(number);
    }

    @Override
    public List<GenreEntity> get() {
        return dao.getGenreList();
    }

    @Override
    public void delete(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Введите номер жанра");
        }
        if (dao.isContain(id)) {
            dao.delete(new GenreEntity(id));
        } else {
            throw new IllegalArgumentException("Нет жанра для удаления с таким id");
        }
    }

    @Override
    public void create(String name) {
        if (name != null && !name.isBlank()) {
            dao.create(new GenreEntity(name));
        } else {
            throw new IllegalArgumentException("Не введен исполнитель");
        }
    }

    @Override
    public void update(int id, GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Не введен жанр");
        }

        if (id == 0) {
            throw new IllegalArgumentException("Введите id жанра");
        }

        if (dao.isContain(id)) {
            GenreEntity genreEntity = new GenreEntity(id, genre);
            dao.update(genreEntity);
        } else {
            throw new IllegalArgumentException("Нет жанра для обновления с таким id");
        }
    }

    @Override
    public String get(Integer id) {
        return null;
    }
}
