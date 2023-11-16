package epam.training.hw.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainingType implements Entity {
    private int id;
    private String name;
}
