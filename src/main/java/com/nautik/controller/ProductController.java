package com.nautik.controller;

import com.nautik.dto.request.ProductRequestDTO;
import com.nautik.dto.response.ProductResponseDTO;
import com.nautik.service.ProductService;
import com.nautik.types.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Encontra o produto pelo id
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(productService.findById(id));
        return ResponseEntity.ok().body(productResponseDTO);
    }

    /**
     * Retorna a lista com todos os produtos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProductList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) Categories category){
        // TODO substituir a chamada do service
        List<ProductResponseDTO> list = productService.getProductList(name, category).stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Altera os dados do produto desejado
     * @param id
     * @param productRequestDTO
     * @return
     */
    @PutMapping (value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(productService.updateProduct(id, productRequestDTO));
        return ResponseEntity.ok().body(productResponseDTO);
    }

    /**
     * Cria um novo produto
     * @param productRequestDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(productService.insertProduct(productRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    /**
     * Deleta o produto
     * @param id
     * @return
     */
    @DeleteMapping (value ="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
