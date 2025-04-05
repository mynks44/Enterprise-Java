package ca.sheridancollege.suranim.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private DiscussionThread thread;

    private String content;
    private String author;

    private Timestamp created;
}
