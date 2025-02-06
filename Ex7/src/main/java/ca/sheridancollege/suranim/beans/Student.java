package ca.sheridancollege.suranim.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data // Lombok annotation to generate getters, setters, toString, etc.
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    private String name;
    private String course;
}