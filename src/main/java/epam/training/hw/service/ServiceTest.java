package epam.training.hw.service;

import epam.training.hw.dao.CrudDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTest {
    private final CrudDaoImpl crudDaoImpl;

    public void test() {
//        traineeDao.displayStorage();
    }
}
