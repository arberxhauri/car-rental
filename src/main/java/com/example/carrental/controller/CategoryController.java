package com.example.carrental.controller;

import com.example.carrental.dto.CategoryDto;
import com.example.carrental.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/findById/{categoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable(name = "categoryId") long categoryId) {
        return ResponseEntity.ok(categoryService.findCategoryById(categoryId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryById(@RequestBody CategoryDto categoryDto,@PathVariable(name = "categoryId") long categoryId) {
        return ResponseEntity.ok(categoryService.updateById(categoryDto,categoryId));
    }

    @DeleteMapping("/deleteById/{categoryId}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "categoryId") long categoryId) {
        categoryService.deleteById(categoryId);

        return ResponseEntity.ok("Category with id: "+ categoryId +" was successfully deleted");
    }

}
