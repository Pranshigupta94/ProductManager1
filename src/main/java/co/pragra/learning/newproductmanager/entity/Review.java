package co.pragra.learning.newproductmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
    @NoArgsConstructor

    @Entity
   // @Table(name="REVIEW_TABLE")
    public class Review{
        @Id

        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String reviews;

    @ManyToOne
    private User user;



    }
