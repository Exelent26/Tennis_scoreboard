package model;

import jakarta.persistence.*;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", nullable = false, length = 120, unique = true)
    private String name;
}
