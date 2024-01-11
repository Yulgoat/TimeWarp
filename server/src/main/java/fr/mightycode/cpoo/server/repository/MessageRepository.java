package fr.mightycode.cpoo.server.repository;

import fr.mightycode.cpoo.server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
  List<Message> findByFromOrToIgnoreCaseOrderByTimestampAsc(String from, String to);

  @Query("SELECT m FROM Message m WHERE (m.from = :user1 AND m.to = :user2) OR (m.from = :user2 AND m.to = :user1) ORDER BY m.timestamp ASC")
  List<Message> findMessagesBetweenUsers(@Param("user1") String user1, @Param("user2") String user2);

  @Modifying
  @Query("UPDATE Message m SET m.from = :newUsername WHERE m.from = :oldUsername")
  void updateUsernameFrom(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

  @Modifying
  @Query("UPDATE Message m SET m.to = :newUsername WHERE m.to = :oldUsername")
  void updateUsernameTo(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername);

}
