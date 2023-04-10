package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.repositories.CategoryRepository;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void shouldGetCategoryById() {
        //TODO - Jogar cada ação em um método e chamá-los dentro do teste getCategoryById().
        //Given - Dados de entrada do teste, dados mockados, repositórios mockados.
        givenGetCategoryById();

        //When - Chamada ao endpoint em questão que sendo testado.
        whenGetCategoryByIdCalled();

        //Then - Verificar se todos os repositories foram testados, verificar se o retorno da chamada está válido.
        thenGetCategoryByIdCalledOnce();
    }

    void shouldGetAllCategories(){}

    void shouldCreateCategory(){}

    void shouldDeleteCategory(){}

    void shouldUpdateCategory(){}

    /*
     * Given methods
     */
    private void givenGetCategoryById() {
        Optional<CategoryDomain> optionalCategory = Optional.of(new CategoryDomain(2L, "Melquisedec"));
        when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);
    }

    /*
     * When methods
     */
    private void whenGetCategoryByIdCalled() {
        categoryService.getCategoryById(anyLong());
    }

    /*
     * Then methods
     */
    private void thenGetCategoryByIdCalledOnce() {
        verify(categoryRepository, times(1)).findById(anyLong());
    }

}