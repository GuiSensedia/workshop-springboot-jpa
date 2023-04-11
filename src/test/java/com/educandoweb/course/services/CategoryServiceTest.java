package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.educandoweb.course.model.dto.request.UpdateCategoryRequest;
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

    @InjectMocks
    private UpdateCategoryRequest updateCategoryRequest;

    @Mock
    private CategoryRepository categoryRepository;

    //Given -> Dados de entrada do teste, dados mockados, repositórios mockados.
    //When  -> Chamada ao endpoint em questão que sendo testado.
    //Then  -> Verificar se todos os repositories foram testados, verificar se o retorno da chamada está válido.
    //TODO Create Delete and Update test;

    @Test
    void shouldGetCategoryById() {
        givenCategoryFindByIdReturnsCategoryDomain();
        whenGetCategoryByIdCalled();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldGetAllCategories(){
        givenCategoriesFindAllReturnsListCategoryDomain();
        whenGetAllCategoriesCalled();
        thenExpectCategoryRepositoryFindAllCalledOnce();
    }

    @Test
    void shouldCreateCategory(){
        givenCreateCategoryRequest();
        whenCreateCategoryCalled();
        thenExpectCategoryRepositorySaveCalledOnce();
    }

    @Test
    void shouldDeleteCategory(){
        whenDeleteCategoryCalled();
        thenExpectCategoryRepositoryDeleteByIdCalledOnce();
    }

    @Test
    void shouldUpdateCategory(){
        givenCategoryFindByIdReturnsCategoryDomain();
        givenUpdateCategoryRequest();
        whenUpdateCategoryCalled();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
        thenExpectCategoryRepositorySaveCalledOnce();
    }

    /*
     * Given methods
     */
    private void givenCategoryFindByIdReturnsCategoryDomain() {
        Optional<CategoryDomain> optionalCategory = Optional.of(new CategoryDomain(2L, "Console"));
        when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);
    }

    private void givenCategoriesFindAllReturnsListCategoryDomain() {
        List<CategoryDomain> listCategory = new ArrayList<>();
        listCategory.add(new CategoryDomain(1L, "Software"));
        listCategory.add(new CategoryDomain(2L, "Hardware"));
        when(categoryRepository.findAll()).thenReturn(listCategory);
    }

    private void givenCreateCategoryRequest() {
        CreateCategoryRequest categoryRequest = new CreateCategoryRequest("Gaming");
        createCategoryRequest = categoryRequest;
    }

    private void givenUpdateCategoryRequest() {
        UpdateCategoryRequest categoryRequest = new UpdateCategoryRequest("Players");
        updateCategoryRequest = categoryRequest;
    }

    /*
     * When methods
     */
    private void whenGetCategoryByIdCalled() {
        categoryService.getCategoryById(1L);
    }

    private void whenGetAllCategoriesCalled() {
        categoryService.getAllCategories();
    }

    private void whenCreateCategoryCalled() {
        categoryService.createCategory(createCategoryRequest);
    }

    private void whenDeleteCategoryCalled() {
        categoryService.deleteCategory(2L);
    }

    private void whenUpdateCategoryCalled() {
        categoryService.updateCategory(3L, updateCategoryRequest);
    }

    /*
     * Then methods
     */
    private void thenExpectCategoryRepositoryFindByIdCalledOnce() {
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    private void thenExpectCategoryRepositoryFindAllCalledOnce() {
        verify(categoryRepository, times(1)).findAll();
    }

    private void thenExpectCategoryRepositorySaveCalledOnce() {
        verify(categoryRepository, times(1)).save(any(CategoryDomain.class));
    }

    private void thenExpectCategoryRepositoryDeleteByIdCalledOnce() {
        verify(categoryRepository).deleteById(anyLong());
    }

}