package com.example.carrental.service;

import com.example.carrental.dto.CategoryDto;
import com.example.carrental.entity.Category;
import com.example.carrental.mapper.CategoryMapper;
import com.example.carrental.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }

    public CategoryDto findCategoryById(long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category with id: " + categoryId + " was not found"));
        return categoryMapper.mapToDto(foundCategory);
    }

    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> returnCategoryDto = new ArrayList<>();
        for (Category category : categories) {
            returnCategoryDto.add(categoryMapper.mapToDto(category));
        }
        return returnCategoryDto;
    }

    public CategoryDto updateById(CategoryDto categoryDto, long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category with id: " + categoryId + " was not found"));
        foundCategory.setId(categoryId);
        foundCategory.setType(categoryDto.getType());
        foundCategory.setPeopleLoad(categoryDto.getPeopleLoad());
        Category updatedCategory = categoryRepository.save(foundCategory);
        return categoryMapper.mapToDto(updatedCategory);
    }


    public void deleteById(long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new RuntimeException("Category with id: " + categoryId + " was not found"));
        categoryRepository.delete(foundCategory);
    }

}
