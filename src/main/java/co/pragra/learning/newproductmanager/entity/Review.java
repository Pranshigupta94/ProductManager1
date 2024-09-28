package co.pragra.learning.newproductmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

@Entity
// @Table(name="REVIEW_TABLE")
public class Review {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reviews;


    @ManyToOne(cascade = CascadeType.PERSIST)

    private User user;

    @ManyToOne
    @JsonIgnore
    private Product product;


}
