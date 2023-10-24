package fr.mightycode.cpoo.server.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "discussions")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ElementCollection
    private List<String> participants;

}
