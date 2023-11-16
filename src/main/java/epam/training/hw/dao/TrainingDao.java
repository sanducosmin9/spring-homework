package epam.training.hw.dao;

import org.springframework.stereotype.Component;

@Component
public class TrainingDao extends CrudDaoImpl{
    public TrainingDao(Storage storage) {
        super(storage, storage.trainingMap);
    }
}
