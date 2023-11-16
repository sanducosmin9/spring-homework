package epam.training.hw.dao;

import org.springframework.stereotype.Component;

@Component
public class TraineeDao extends CrudDaoImpl{
    public TraineeDao(Storage storage) {
        super(storage, storage.traineeMap);
    }
}
