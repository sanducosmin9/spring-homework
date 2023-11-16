package epam.training.hw.dao;

import epam.training.hw.entities.Entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CrudDaoImpl implements CrudDao<Entity>{

    private final Storage storage;
    private final Map<Integer, Entity> entityMap;
    @Override
    public Entity findById(int id) {
        var result = entityMap.get(id);
        log.info(result != null ? "Trainee retrieved successfully" : "Trainee with id: " + id + " not found");
        return entityMap.get(id);
    }

    @Override
    public List<Entity> findAll() {
        log.info("Retrieved " + entityMap.size() + " trainees");
        return entityMap.values().stream().toList();
    }

    @Override
    public void add(Entity entity) {
        if(entity.getId() >= storage.lastId) {
            log.info("Entity with id " + entity.getId() + " was added successfully");
            entityMap.put(entity.getId(), entity);
        } else {
            log.info("Entity with id " + entity.getId() + " already exists. Creating entity with new id");
            entity.setId(++storage.lastId);
            entityMap.put(storage.lastId, entity);
        }
    }

    @Override
    public void update(Entity entity) {
        if(this.findById(entity.getId()) == null) {
            log.error("Entity with id " + entity.getId() + " doesn't exist");
            return;
        }
        entityMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(int id) {
        if(this.findById(id) == null) {
            log.error("Entity with id " + id + " doesn't exist");
            return;
        }
        entityMap.remove(id);
    }
}
