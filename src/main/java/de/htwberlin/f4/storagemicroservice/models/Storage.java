package de.htwberlin.f4.storagemicroservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Period;
import java.util.UUID;

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
    @Column(name = "delivery_time", columnDefinition = "interval")
    private Period deliveryTime;
    @Column(name = "place", columnDefinition = "TEXT")
    private String place;
}