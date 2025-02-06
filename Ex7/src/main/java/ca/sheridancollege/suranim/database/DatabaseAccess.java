package ca.sheridancollege.suranim.database;

import ca.sheridancollege.suranim.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DatabaseAccess extends JpaRepository<Student, Long> {
    // Custom query to find students by course (example)
    List<Student> findByCourse(String course);
}