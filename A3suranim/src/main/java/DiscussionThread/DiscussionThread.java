package DiscussionThread;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discussion_thread") // <-- force Hibernate to use this table name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
}
