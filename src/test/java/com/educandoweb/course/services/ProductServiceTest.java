package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.domain.ProductDomain;
import com.educandoweb.course.model.dto.request.CreateProductRequest;
import com.educandoweb.course.model.dto.request.UpdateProductRequest;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private ProductService productService;

    @InjectMocks
    private CreateProductRequest createProductRequest;

    @InjectMocks
    private UpdateProductRequest updateProductRequest;

    //Given -> Dados de entrada do teste, dados mockados, repositórios mockados.
    //When  -> Chamada ao endpoint em questão que sendo testado.
    //Then  -> Verificar se todos os repositories foram testados, verificar se o retorno da chamada está válido.

    @Test
    void shouldGetAllProducts() {
        givenProductFindAllReturnsListProductDomain();
        whenGetAllProductsCalled();
        thenExpectedProductRepositoryFindAllCalledOnce();
    }

    @Test
    void shouldGetProductById() {
        givenProductFindByIdReturnsProductDomain();
        whenGetProductByIdCalled();
        thenExpectProductRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenFindProductByIdNotFound() {
        givenProductFindByIdReturnsEmpty();
        whenGetProductByIdCalledThrowsResourceNotFoundException();
        thenExpectProductRepositoryFindByIdCalledOnce();
    }
    // Category is Null
    @Test
    void shouldCreateProduct() {
        givenCreateProductRequest();
        givenCreateCategoryRepositoryFindByIdReturnsCategoryDomain();
        whenCreateProductCalled();
        thenExpectProductRepositorySaveCalledOnce();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
    }

    // OrdemItem is Null
    @Test
    void shouldDeleteProduct() {
        whenDeleteProductCalled();
        thenExpectOrderItemRepositoryDeleteByProductIdCalledOnce();
        thenExpectProductRepositoryDeleteByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenDeleteProductNotFound() {
        givenProductRepositoryDeleteByIdThrowsException();
        whenProductDeleteByIdCalledThrowsResourceNotFoundException();
        thenExpectProductRepositoryDeleteByIdCalledOnce();
    }
    // Category is Null
    @Test
    void shouldUpdateProduct() {
        givenProductFindByIdReturnsProductDomain();
        givenUpdateProductRequest();
        givenCreateCategoryRepositoryFindByIdReturnsCategoryDomain();
        whenUpdateProductCalled();
        thenExpectProductRepositoryFindByIdCalledOnce();
        thenExpectProductRepositorySaveCalledOnce();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUpdateProductIdNotFound() {
        givenProductFindByIdReturnsEmpty();
        whenGetProductByIdForUpdateCalledThrowsResourceNotFoundException();
        thenExpectProductRepositoryFindByIdCalledOnce();
    }

    /*
     * GIVEN METHODS:
     */

    private void givenProductFindAllReturnsListProductDomain() {
        List<ProductDomain> listProductDomain = new ArrayList<>();
        ProductDomain productDomain1 = ProductDomain.builder()
                .name("Led Tape")
                .description("Led Tape,RGB Collors, 5 Meters.")
                .price(30.00)
                .category(new CategoryDomain(1L, "Decoration"))
                .build();
        ProductDomain productDomain2 = ProductDomain.builder()
                .name("Monitor")
                .description("Monitor For Gaming")
                .price(1000.00)
                .category(new CategoryDomain(2L, "Peripheral"))
                .build();
        listProductDomain.add(productDomain1);
        listProductDomain.add(productDomain2);
        when(productRepository.findAll()).thenReturn(listProductDomain);
    }

    private void givenProductFindByIdReturnsProductDomain() {
        Optional<ProductDomain> optionalProductDomain = Optional.of(ProductDomain.builder()
                .name("Led Tape")
                .description("Led Tape,RGB Collors, 5 Meters.")
                .price(30.00)
                .category(new CategoryDomain(1L, "Decoration"))
                .build());
        when(productRepository.findById(anyLong())).thenReturn(optionalProductDomain);
    }

    private void givenProductFindByIdReturnsEmpty() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    }

    private void givenCreateProductRequest() {
        CreateProductRequest productRequest = CreateProductRequest.builder()
                .name("Monitor")
                .description("Monitor For Gaming")
                .price(1000.00)
                .idCategory(1L)
                .build();
        createProductRequest = productRequest;
    }

    private void givenCreateCategoryRepositoryFindByIdReturnsCategoryDomain() {
        Optional<CategoryDomain> categoryDomain = Optional.of(new CategoryDomain(1L,"Eletronics"));
        when(categoryRepository.findById(anyLong())).thenReturn(categoryDomain);
    }

    private void givenUpdateProductRequest() {
        UpdateProductRequest productRequest = UpdateProductRequest.builder()
                .name("Monitor UHD")
                .description("Monitor For Gaming")
                .price(1500.00)
                .idCategory(2L)
                .build();
        updateProductRequest = productRequest;
    }

    private void givenProductRepositoryDeleteByIdThrowsException() {
        doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(anyLong());
    }

    /*
     * WHEN METHODS:
     */

    private void whenGetAllProductsCalled() {
        productService.getAllProducts();
    }

    private void whenGetProductByIdCalled() {
        productService.getProductById(1L);
    }

    private void whenGetProductByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(2L));
    }

    private void whenCreateProductCalled() {
        productService.createProduct(createProductRequest);
    }

    private void whenDeleteProductCalled() {
        productService.deleteProduct(3L);
    }

    private void whenProductDeleteByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(4L));
    }

    private void whenUpdateProductCalled() {
        productService.updateProduct(5L, updateProductRequest);
    }

    private void whenGetProductByIdForUpdateCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(6L));
    }

    /*
     * THEN METHODS:
     */

    private void thenExpectedProductRepositoryFindAllCalledOnce() {
        verify(productRepository,times(1)).findAll();
    }

    private void thenExpectProductRepositoryFindByIdCalledOnce() {
        verify(productRepository, times(1)).findById(anyLong());
    }

    private void thenExpectProductRepositorySaveCalledOnce() {
        verify(productRepository, times(1)).save(any(ProductDomain.class));
    }

    private void thenExpectProductRepositoryDeleteByIdCalledOnce() {
        verify(productRepository, times(1)).deleteById(anyLong());
    }

    private void thenExpectOrderItemRepositoryDeleteByProductIdCalledOnce() {
        verify(orderItemRepository, times(1)).deleteByProductId(anyLong());
    }

    private void thenExpectCategoryRepositoryFindByIdCalledOnce() {
        verify(categoryRepository, times(1)).findById(anyLong());
    }


}