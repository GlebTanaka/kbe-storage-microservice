package de.htwberlin.f4.storagemicroservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @NotNull
    private UUID id;
    @Column(name = "amount")
    @NotNull
    @Positive
    private Integer amount;
    @Positive
    @NotNull
    @Column(name = "duration")
    private Integer duration;
    @NotBlank
    @Column(name = "place", columnDefinition = "TEXT")
    private String place;
}
