package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingerDaoTest {
    private ISingerDao dao = new SingerDao();

    @Test
    public void testSingerDaoTrue() {
        int numberSingerTrue = 1;
        boolean result = dao.isContain(numberSingerTrue);
        assertTrue(result);
    }

    @Test
    public void testSingerDaoFalse() {
        int numberSingerFalse = 5;
        boolean result = dao.isContain(numberSingerFalse);
        assertFalse(result);
    }


}