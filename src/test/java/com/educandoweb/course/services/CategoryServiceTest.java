package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.educandoweb.course.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @InjectMocks
    private CreateCategoryRequest createCategoryRequest;

    @Mock
    private CategoryRepository categoryRepository;

    //Given -> Dados de entrada do teste, dados mockados, repositórios mockados.
    //When  -> Chamada ao endpoint em questão que sendo testado.
    //Then  -> Verificar se todos os repositories foram testados, verificar se o retorno da chamada está válido.
    //TODO Create Delete and Update test;

    @Test
    void shouldGetCategoryById() {
        givenGetCategoryById();
        whenGetCategoryByIdCalled();
        thenExpectedGetCategoryByIdCalledOnce();
    }

    @Test
    void shouldGetAllCategories(){
        givenGetAllCategories();
        whenGetAllCategoriesCalled();
        thenExpectedGetAllCategoriesCalledOnce();
    }

    @Test
    void shouldCreateCategory(){
        givenCreateCategory();
        whenCreateCategoryCalled();
        thenExpectedCreateCategoryCalledOnce();
    }

    @Test
    void shouldDeleteCategory(){}

    @Test
    void shouldUpdateCategory(){}

    /*
     * Given methods
     */
    private void givenGetCategoryById() {
        Optional<CategoryDomain> optionalCategory = Optional.of(new CategoryDomain(2L, "Console"));
        when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);
    }

    private void givenGetAllCategories() {
        List<CategoryDomain> listCategory = new ArrayList<>();
        listCategory.add(new CategoryDomain(1L, "Software"));
        listCategory.add(new CategoryDomain(2L, "Hardware"));
        when(categoryRepository.findAll()).thenReturn(listCategory);
    }

    private void givenCreateCategory() {
        CategoryDomain domain = new CategoryDomain(5L, "Decoration");
        when(categoryRepository.save(domain));
    }

    /*
     * When methods
     */
    private void whenGetCategoryByIdCalled() {
        categoryService.getCategoryById(anyLong());
    }

    private void whenGetAllCategoriesCalled() {
        categoryService.getAllCategories();
    }

    private void whenCreateCategoryCalled() {
        categoryService.createCategory(createCategoryRequest);
    }

    /*
     * Then methods
     */
    private void thenExpectedGetCategoryByIdCalledOnce() {
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    private void thenExpectedGetAllCategoriesCalledOnce() {
        verify(categoryRepository, times(1)).findAll();
    }

    private void thenExpectedCreateCategoryCalledOnce() {
        verify(categoryRepository, times(1)).save(any(CategoryDomain.class));
    }

}