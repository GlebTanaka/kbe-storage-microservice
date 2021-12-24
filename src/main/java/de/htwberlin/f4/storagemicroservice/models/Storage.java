package de.htwberlin.f4.storagemicroservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Model der Entitaet Storage
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(value = AccessLevel.PUBLIC)
@Entity
@Table
public class Storage {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "amount")
    private Integer amount;
    //TODO change datatype to Period or Duration?
    @Column(name = "delivery")
    private Integer delivery;
    @Column(name = "place", columnDefinition = "TEXT")
    private String place;
}
