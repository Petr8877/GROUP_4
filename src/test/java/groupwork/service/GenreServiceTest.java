package groupwork.service;

import groupwork.dao.GenreDao;
import groupwork.dao.api.IGenreDao;
import groupwork.service.api.IGenreService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreServiceTest {
    private final IGenreDao dao = new GenreDao();
    private final IGenreService genreService = new GenreService(dao);


    @Test
    public void testGenreServiceTrue() {
        boolean result = genreService.check(1);
        assertTrue(result);
    }
    @Test
    public void testGenreServiceFalse() {
        boolean result = genreService.check(11);
        assertFalse(result);
    }
}