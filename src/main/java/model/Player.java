package model;

import lombok.*;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class Player {
    private UUID id;
    private String name;
}
