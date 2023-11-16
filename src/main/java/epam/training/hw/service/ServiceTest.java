package epam.training.hw.service;

import epam.training.hw.dao.TraineeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTest {
    private final TraineeDao traineeDao;

    public void test() {
        traineeDao.displayStorage();
    }
}
