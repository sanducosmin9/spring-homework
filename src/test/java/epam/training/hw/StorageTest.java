package epam.training.hw;

import epam.training.hw.dao.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StorageTest {
    @Autowired
    private Storage storage;

    @Test
    public void testDataLoading() {
        storage.initialize();
        Assertions.assertNotNull(storage.traineeMap);
        Assertions.assertNotNull(storage.trainerMap);
        Assertions.assertNotNull(storage.trainingMap);
    }

}
