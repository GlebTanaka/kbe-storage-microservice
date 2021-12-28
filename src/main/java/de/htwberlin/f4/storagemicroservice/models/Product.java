package de.htwberlin.f4.storagemicroservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(value = AccessLevel.PUBLIC)
@Entity
@Table
public class Product {

    @Id
    @Column(name = "id", updatable = false)
    private String id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "size", nullable = false, columnDefinition = "TEXT")
    private String size;
    @Column(name = "color", nullable = false, columnDefinition = "TEXT")
    private String color;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "weight", nullable = false)
    private Double weight;
    @Column(name = "place", nullable = false, columnDefinition = "TEXT")
    private String place;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @Column(name = "mehrwertsteuer", nullable = false)
    private Double mehrwertsteuer;
    @Column(name = "formatted_address", nullable = false, columnDefinition = "TEXT")
    private String formattedAddress;
    @Column(name = "delivery_date", nullable = false)
    private Date deliveryDate;

}
