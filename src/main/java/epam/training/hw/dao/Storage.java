package epam.training.hw.dao;

import epam.training.hw.entities.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Storage {

    @Value("${trainer.path}")
    private String trainerFilePath;

    @Value("${trainee.path}")
    private String traineeFilePath;

    @Value("${training.path}")
    private String trainingFilePath;

    public final Map<Integer, Entity> trainerMap;
    public final Map<Integer, Entity> traineeMap;
    public final Map<Integer, Entity> trainingMap;
    public int lastId = 10;

    public Storage() {
        trainerMap = new HashMap<>();
        traineeMap = new HashMap<>();
        trainingMap = new HashMap<>();
    }

    @PostConstruct
    public void initialize(){
        loadDataFromCSV(trainerFilePath, "trainer");
        loadDataFromCSV(traineeFilePath, "trainee");
        loadDataFromCSV(trainingFilePath, "training");
        log.info("--------Storage initialized---------");
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
            log.error("IO exception for the " + entityType + ".csv");
        }
    }
}
