package ca.sheridancollege.suranim.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.suranim.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByThreadId(int threadId);
}
