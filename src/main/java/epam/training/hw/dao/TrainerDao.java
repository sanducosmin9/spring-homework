package epam.training.hw.dao;

import org.springframework.stereotype.Component;

@Component
public class TrainerDao extends CrudDaoImpl{
    public TrainerDao(Storage storage) {
        super(storage, storage.trainerMap);
    }
}
