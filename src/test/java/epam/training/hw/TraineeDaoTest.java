package epam.training.hw;

import epam.training.hw.dao.Storage;
import epam.training.hw.dao.TraineeDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TraineeDaoTest {

    @Autowired
    private Storage storage;
    @Autowired
    private TraineeDao traineeDao;

    @Test
    public void returnTraineeWhenIdIsCorrect() {
        var result = traineeDao.findById(5);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(5);
    }

    @Test
    public void returnNullWhenIdIsIncorrect() {
        var result = traineeDao.findById(-2);
        Assertions.assertThat(result).isNull();
    }
}
