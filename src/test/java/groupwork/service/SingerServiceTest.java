package groupwork.service;

import groupwork.dao.GenreDao;
import groupwork.dao.SingerDao;
import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISingerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingerServiceTest {
    private final ISingerDao singerDao = new SingerDao();
    private final ISingerService singerService = new SingerService(singerDao);


    @Test
    public void testSingerServiceTrue() {
        boolean result = singerService.checkNumber(1);
        assertTrue(result);
    }

    @Test
    public void testSingerServiceFalse() {
        boolean result = singerService.checkNumber(11);
        assertFalse(result);
    }

}