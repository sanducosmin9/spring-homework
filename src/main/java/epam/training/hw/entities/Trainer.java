package epam.training.hw.entities;

import lombok.Data;

@Data
public class Trainer implements Entity{
    private int id;
    private TrainingType trainingType;
}
