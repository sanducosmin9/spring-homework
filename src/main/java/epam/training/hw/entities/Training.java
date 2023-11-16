package epam.training.hw.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Training implements Entity {
    private int id;
    private int traineeId;
    private int trainerId;
    private String trainingName;
    private int trainingTypeId;
    private LocalDate date;
    private int duration;
}
