package com.nautik.entities;

import com.nautik.dto.request.ProductRequestDTO;
import com.nautik.types.Categories;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column
    private Categories category;

    public Product(){
    }

    public Product(String name, String description, Categories category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Product(ProductRequestDTO productRequestDTO){
        this.name = productRequestDTO.getName();
        this.description = productRequestDTO.getDescription();
        this.category = productRequestDTO.getCategory();
    }

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
