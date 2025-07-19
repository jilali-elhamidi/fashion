package com.store.fashion.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    private String gender;
    private String category;
    private String subcategory;

    @Column(name = "article_type")
    private String articleType;

    @Column(name = "base_color")
    private String baseColor;

    private String usage;

    @Column(name = "product_title")
    private String productTitle;

    @Column(length = 500)
    private String image;       // colonne Image (local ou autre)

    @Column(name = "image_url", length = 1000)
    private String imageUrl;    // colonne ImageURL (souvent URL web)

    // getters/setters
}