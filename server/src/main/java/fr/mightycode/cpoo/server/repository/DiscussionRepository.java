package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, UUID>{
    List<Discussion> findDiscussionsByParticipantsContains(String username);
    List<Discussion> findDiscussionsByParticipants(List<String> participants);
}
