package de.htwberlin.f4.storagemicroservice.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import java.time.LocalDateTime;
import java.sql.Date;
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
    private UUID id;
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
    @Column(name = "place", columnDefinition = "TEXT")
    private String place;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "mehrwertsteuer")
    private Double mehrwertsteuer;
    @Column(name = "formatted_address", columnDefinition = "TEXT")
    private String formattedAddress;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "delivery_time")
    private Integer deliveryTime;

}
