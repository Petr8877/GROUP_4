package groupwork.dao;

import groupwork.dao.api.IGenreDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreDaoTest {
    private final IGenreDao dao = new GenreDao();


    @Test
    public void testGenreDaoTrue() {
        int numberGenreTrue = 1;
        boolean result = dao.isContain(numberGenreTrue);
        assertTrue(result);
    }

    @Test
    public void testGenreDaoFalse() {
        int numberGenreFalse = 11;
        boolean result = dao.isContain(numberGenreFalse);
        assertFalse(result);
    }
}