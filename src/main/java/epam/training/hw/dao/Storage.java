package epam.training.hw.dao;

import epam.training.hw.entities.Trainee;
import epam.training.hw.entities.Trainer;
import epam.training.hw.entities.Training;
import epam.training.hw.entities.TrainingType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class Storage {

    public final Map<Integer, Trainer> trainerMap;
    public final Map<Integer, Trainee> traineeMap;
    public final Map<Integer, Training> trainingMap;

    public Storage() {
        trainerMap = new HashMap<>();
        traineeMap = new HashMap<>();
        trainingMap = new HashMap<>();
    }

    @PostConstruct
    public void initialize(){
        loadDataFromCSV("./trainer.csv", "trainer");
        loadDataFromCSV("./trainee.csv", "trainee");
        loadDataFromCSV("./training.csv", "training");
    }

    public void loadDataFromCSV(String filePath, String entityType) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                switch (entityType) {
                    case "trainer" -> {
                        Trainer trainer = new Trainer();
                        trainer.setId(Integer.parseInt(values[0]));
                        trainer.setTrainingType(new TrainingType(Integer.parseInt(values[1]), values[2]));
                        trainerMap.put(trainer.getId(), trainer);
                    }
                    case "trainee" -> {
                        Trainee trainee = new Trainee();
                        trainee.setId(Integer.parseInt(values[0]));
                        trainee.setDateOfBirth(LocalDate.parse(values[1]));
                        trainee.setAddress(values[2]);
                        traineeMap.put(trainee.getId(), trainee);
                    }
                    case "training" -> {
                        Training training = new Training();
                        training.setId(Integer.parseInt(values[0]));
                        training.setTraineeId(Integer.parseInt(values[1]));
                        training.setTrainerId(Integer.parseInt(values[2]));
                        training.setTrainingName(values[3]);
                        training.setTrainingTypeId(Integer.parseInt(values[4]));
                        training.setDate(LocalDate.parse(values[5]));
                        training.setDuration(Integer.parseInt(values[6]));
                        trainingMap.put(training.getId(), training);
                    }
                    default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
