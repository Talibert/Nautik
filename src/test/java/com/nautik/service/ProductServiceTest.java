package com.nautik.service;

import com.nautik.dto.request.ProductRequestDTO;
import com.nautik.entities.Product;
import com.nautik.exceptions.ProductNotFoundException;
import com.nautik.repositories.ProductRepository;
import com.nautik.types.Categories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductRequestDTO productRequestDTO;

    @Mock
    private Product product;

    @Spy
    @InjectMocks
    private ProductService productService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void init(){
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        autoCloseable.close();
    }

    /**
     * Verifica se a chamada do repository irá acontecer.
     */
    @Test
    public void testeFindById(){
        Optional<Product> productOptional = Optional.of(product);
        Long id = 1L;
        Product expected = productOptional.get();
        Mockito.when(productRepository.findById(id)).thenReturn(productOptional);

        Product result = productService.findById(id);

        assertEquals(expected, result);
        Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    }

    /**
     * Captura uma exception lançada por não encontrar o produto e verifica se a mensagem está correta
     */
    @Test
    public void testeFindByIdException(){
        Optional<Product> productOptional = Optional.empty();
        Long id = 1L;
        Mockito.when(productRepository.findById(id)).thenReturn(productOptional);
        String expectedMessage = "O produto de id: " + id + " não existe!";

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(id);
        });

        String resultMessage = exception.getMessage();

        assertEquals(expectedMessage, resultMessage);

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    }

    /**
     * Verifica se a chamada do repository filtrada pelo Category irá acontecer.
     */
    @Test
    public void testeGetProductByCategory(){
        List<Product> expectedList = List.of(product);

        Mockito.when(productRepository.findByCategory(Mockito.any())).thenReturn(expectedList);

        List<Product> resultList = productService.getProductList(null, Categories.MOTORES);

        assertEquals(expectedList, resultList);

        Mockito.verify(productRepository, Mockito.times(1)).findByCategory(Mockito.any());
    }

    /**
     * Verifica se a chamada do repository sem filtros irá acontecer.
     */
    @Test
    public void testeGetProductList(){
        List<Product> expectedList = List.of(product);

        Mockito.when(productRepository.findAll()).thenReturn(expectedList);

        List<Product> resultList = productService.getProductList(null, null);

        assertEquals(expectedList, resultList);

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    /**
     * Captura uma exception lançada por não encontrar nenhum curso e verifica se a mensagem está correta
     */
    @Test
    public void testeGetProductListException(){
        List<Product> emptyList = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(emptyList);
        String expectedMessage = "Nenhum produto encontrado";

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductList(null, null);
        });

        String resultMessage = exception.getMessage();

        assertEquals(expectedMessage, resultMessage);

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }
}