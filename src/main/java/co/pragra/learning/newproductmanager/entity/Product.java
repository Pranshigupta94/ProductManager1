package co.pragra.learning.newproductmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor

@Entity
//@Table(name="PRODUCT_TABLE")
public class Product {
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String productName;
    private String description;
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Review> reviews;

}
