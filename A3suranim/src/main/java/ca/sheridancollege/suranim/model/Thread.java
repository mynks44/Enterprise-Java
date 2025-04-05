package ca.sheridancollege.suranim.model;

import lombok.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {
    private int id;
    private String title;
    private String createdBy;
    private Timestamp createdAt;
}
