package epam.training.hw.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraineeDao {
    private final Storage storage;

    public void displayStorage() {
        System.out.println(storage.traineeMap);
    }
}
