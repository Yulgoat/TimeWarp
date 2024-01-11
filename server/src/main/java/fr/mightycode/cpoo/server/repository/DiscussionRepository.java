package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, UUID> {
  List<Discussion> findDiscussionsByUser1OrUser2OrderByTimestampAsc(String user1, String user2);

  List<Discussion> findDiscussionsByUser1AndUser2OrderByTimestampAsc(String user1, String user2);

  @Modifying
  @Query("UPDATE Discussion d SET d.timestamp = :timestamp WHERE d.id = :id")
  void updateTimestampById(@Param("id") UUID id, @Param("timestamp") long timestamp);

  @Modifying
  @Query("UPDATE Discussion d SET d.unreadMessage = :unreadMessage WHERE d.id = :id")
  void updateUnreadMessageById(@Param("id") UUID id, @Param("unreadMessage") boolean unreadMessage);

  @Modifying
  @Query("UPDATE Discussion d SET d.user1 = :newUsername WHERE d.user1 = :oldUsername")
  void updateUsernameInUser1(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

  @Modifying
  @Query("UPDATE Discussion d SET d.user2 = :newUsername WHERE d.user2 = :oldUsername")
  void updateUsernameInUser2(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);


}
