package epam.training.hw.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Trainee implements Entity{
    private int id;
    private LocalDate dateOfBirth;
    private String address;
}
