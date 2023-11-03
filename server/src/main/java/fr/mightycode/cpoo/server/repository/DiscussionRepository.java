package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, UUID>{
    List<Discussion> findDiscussionsByUser1OrUser2(String user1, String user2);
    List<Discussion> findDiscussionsByUser1AndUser2(String user1, String user2);
}
