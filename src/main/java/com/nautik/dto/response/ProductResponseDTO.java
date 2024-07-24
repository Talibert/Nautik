package com.nautik.dto.response;

import com.nautik.entities.Product;
import com.nautik.types.Categories;

public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Categories category;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
    }

    public Long getId() {
        return id;
    }

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
