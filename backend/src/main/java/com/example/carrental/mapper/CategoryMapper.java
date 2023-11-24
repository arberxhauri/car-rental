package com.example.carrental.mapper;

import com.example.carrental.dto.CategoryDto;
import com.example.carrental.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CategoryMapper {

    public Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setType(categoryDto.getType());
        category.setPeopleLoad(categoryDto.getPeopleLoad());
        return category;

    }
    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setType(category.getType());
        categoryDto.setPeopleLoad(category.getPeopleLoad());
        return categoryDto;
    }
}
