package ca.sheridancollege.suranim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ca.sheridancollege.suranim.model.DiscussionThread;

public interface DiscussionThreadRepository extends JpaRepository<DiscussionThread, Integer> {
}
