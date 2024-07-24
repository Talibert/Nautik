package com.nautik.config;

import com.nautik.entities.Product;
import com.nautik.repositories.ProductRepository;
import com.nautik.types.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


/**
 * A interface CommandLineRunner força a implementação do método run.
 * Esse método será executado sempre que a aplicação rodar, como se fosse um método main.
 * Dessa forma, podemos instanciar os objetos sem necessariamente poluir o nosso método main
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Product product1 = new Product("Hélice", "Hélice para barco", Categories.HELICES);
        Product product2 = new Product("Motor", "Motor de barco", Categories.MOTORES);
        Product product3 = new Product("Acessório de pesca", "acessórios para pesca", Categories.ACESSORIOS);

        List<Product> products = List.of(product1, product2, product3);

        productRepository.saveAll(products);
    }
}