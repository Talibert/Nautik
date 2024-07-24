package com.nautik.dto.request;

import com.nautik.types.Categories;

public class ProductRequestDTO {

    private String name;

    private String description;

    private Categories category;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, String description, Categories category) {
        this.name = name;
        this.description = description;
        this.category = category;
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
