package fr.mightycode.cpoo.server.model;

import lombok.Data;

import java.util.UUID;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "discussions")
public class Discussion {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "timestamp", nullable = false)
  private long timestamp;

  @Column(name = "user1", nullable = false)
  private String user1;

  @Column(name = "user2", nullable = false)
  private String user2;

  @Column(name = "unreadMessage", nullable = false)
  private boolean unreadMessage = false;

}
