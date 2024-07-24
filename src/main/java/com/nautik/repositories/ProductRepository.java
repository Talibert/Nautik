package com.nautik.repositories;

import com.nautik.entities.Product;
import com.nautik.types.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Categories category);

    List<Product> findByName(String name);
}
