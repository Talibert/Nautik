package com.nautik.service;

import com.nautik.dto.request.ProductRequestDTO;
import com.nautik.entities.Product;
import com.nautik.exceptions.ProductNotFoundException;
import com.nautik.types.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private com.nautik.repositories.ProductRepository productRepository;

    /**
     * Encontra um produto através do Id recebido
     * @param id
     * @return
     */
    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("O produto de id: " + id + " não existe!");
        }

        return product.get();
    }

    /**
     * Retorna uma lista de produtos que pode ser filtrado pela categoria ou pelo nome
     * @return
     */
    public List<Product> getProductList(String name, Categories category){
        List<Product> productList;

        if (name != null) {
            productList = productRepository.findByName(name);
        } else if (category != null) {
            productList = productRepository.findByCategory(category);
        } else {
            productList = productRepository.findAll();
        }

        if (productList.isEmpty()){
            throw new ProductNotFoundException("Nenhum produto encontrado");
        }

        return productList;
    }

    /**
     * Altera um produto existente
     * @param id
     * @param productRequestDTO
     * @return
     */
    public Product updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = findById(id);

        if(productRequestDTO.getName() != null){
            product.setName(productRequestDTO.getName());
        }
        if(productRequestDTO.getDescription() != null){
            product.setDescription(productRequestDTO.getDescription());
        }
        if(productRequestDTO.getCategory() != null){
            product.setCategory(productRequestDTO.getCategory());
        }

        return productRepository.save(product);
    }

    /**
     * Cria um novo produto
     * @param productRequestDTO
     * @return
     */
    public Product insertProduct(ProductRequestDTO productRequestDTO){
        Product product = new Product(productRequestDTO);
        return productRepository.save(product);
    }

    /**
     * Deleta um produto através do id recebido
     * @param id
     */
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
