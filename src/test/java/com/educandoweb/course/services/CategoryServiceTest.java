package com.educandoweb.course.services;

import com.educandoweb.course.model.domain.CategoryDomain;
import com.educandoweb.course.model.dto.request.CreateCategoryRequest;
import com.educandoweb.course.model.dto.request.UpdateCategoryRequest;
import com.educandoweb.course.repositories.CategoryRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
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

    @Test
    void shouldGetCategoryById() {
        givenCategoryFindByIdReturnsCategoryDomain();
        whenGetCategoryByIdCalled();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenFindCategoryByIdNotFound() {
        givenCategoryFindByIdReturnsEmpty();
        whenGetCategoryByIdCalledThrowsResourceNotFoundException();
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
    void shouldThrowResourceNotFoundExceptionWhenDeleteCategoryNotFound() {
        givenCategoryRepositoryDeleteByIdThrowsException();
        whenCategoryDeleteByIdCalledThrowsResourceNotFoundException();
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

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUpdateCategoryIdNotFound() {
        givenCategoryFindByIdReturnsEmpty();
        whenGetCategoryByIdForUpdateCalledThrowsResourceNotFoundException();
        thenExpectCategoryRepositoryFindByIdCalledOnce();
    }

    /*
     * Given methods
     */

    private void givenCategoryFindByIdReturnsCategoryDomain() {
        Optional<CategoryDomain> optionalCategory = Optional.of(new CategoryDomain(2L, "Console"));
        when(categoryRepository.findById(anyLong())).thenReturn(optionalCategory);
    }

    private void givenCategoryFindByIdReturnsEmpty() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
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

    private void givenCategoryRepositoryDeleteByIdThrowsException() {
        doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(anyLong());
    }

    /*
     * When methods
     */

    private void whenGetCategoryByIdCalled() {
        categoryService.getCategoryById(1L);
    }

    private void whenGetCategoryByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1L));
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

    private void whenCategoryDeleteByIdCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> categoryService.deleteCategory(3L));
    }

    private void whenUpdateCategoryCalled() {
        categoryService.updateCategory(4L, updateCategoryRequest);
    }

    private void whenGetCategoryByIdForUpdateCalledThrowsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1L));
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