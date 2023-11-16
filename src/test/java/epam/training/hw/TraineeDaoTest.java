package epam.training.hw;

import epam.training.hw.dao.Storage;
import epam.training.hw.dao.CrudDaoImpl;
import epam.training.hw.dao.TraineeDao;
import epam.training.hw.entities.Trainee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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

    @Test
    public void returnAllEntities() {
        var result = traineeDao.findAll();
        Assertions.assertThat(result.size()).isEqualTo(10);
    }

    @Test
    public void givenAnEntityWhenAddThenCreateNewEntityWithLastSavedId() {
        traineeDao.add(getTrainee());
        Assertions.assertThat(storage.lastId).isEqualTo(11);
        var addedEntity = traineeDao.findById(storage.lastId);
        Assertions.assertThat(addedEntity).isNotNull();
    }

    @Test
    public void givenAnEntityWhenUpdateThenUpdateAddress() {
        var trainee = getTrainee();
        traineeDao.update(trainee);
        Trainee updatedTrainee = (Trainee) traineeDao.findById(1);
        Assertions.assertThat(updatedTrainee.getAddress()).isEqualTo(trainee.getAddress())
    }

    public Trainee getTrainee() {
        return new Trainee(
                1,
                LocalDate.of(1995, 1, 10),
                "ADDR"
        );
    }
}
