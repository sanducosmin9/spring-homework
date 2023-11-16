package epam.training.hw.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee implements Entity{
    private int id;
    private LocalDate dateOfBirth;
    private String address;
}
