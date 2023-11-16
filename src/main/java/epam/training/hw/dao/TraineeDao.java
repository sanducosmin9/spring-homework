package epam.training.hw.dao;

import epam.training.hw.entities.Trainee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TraineeDao implements CrudDao<Trainee>{

    private final Storage storage;

    @Override
    public Trainee findById(int id) {
        var result = storage.traineeMap.get(id);
        log.info(result != null ? "Trainee retrieved successfully" : "Trainee with id: " + id + " not found");
        return storage.traineeMap.get(id);
    }

    @Override
    public List<Trainee> findAll() {
        log.info("Retrieved " + storage.traineeMap.size() + " trainees");
        return storage.traineeMap.values().stream().toList();
    }

    @Override
    public void add(Trainee entity) {
        if(entity.getId() >= storage.lastId) {
            log.info("Entity with id " + entity.getId() + " was added successfully");
            storage.traineeMap.put(entity.getId(), entity);
        } else {
            log.info("Entity with id " + entity.getId() + " already exists. Creating entity with new id");
            entity.setId(++storage.lastId);
            storage.traineeMap.put(storage.lastId, entity);
        }
    }

    @Override
    public void update(Trainee entity) {
        if(this.findById(entity.getId()) == null) {
            log.error("Entity with id " + entity.getId() + " doesn't exist");
            return;
        }
        storage.traineeMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(int id) {
        if(this.findById(id) == null) {
            log.error("Entity with id " + id + " doesn't exist");
            return;
        }
        storage.traineeMap.remove(id);
    }
}
