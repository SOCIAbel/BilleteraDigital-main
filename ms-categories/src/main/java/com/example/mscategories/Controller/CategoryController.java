package com.example.mscategories.Controller;

import com.example.mscategories.DTO.CategoryRequestDTO;
import com.example.mscategories.DTO.CategoryResponseDTO;
import com.example.mscategories.DTO.SubcategoryRequestDTO;
import com.example.mscategories.DTO.SubcategoryResponseDTO;
import com.example.mscategories.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // ==========================
    // CATEGORÍAS
    // ==========================
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.getCategoriesByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // ==========================
    // SUBCATEGORÍAS
    // ==========================
    @PostMapping("/{categoryId}/subcategories")
    public ResponseEntity<SubcategoryResponseDTO> createSubcategory(
            @PathVariable Long categoryId,
            @RequestBody SubcategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createSubcategory(categoryId, request));
    }

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<SubcategoryResponseDTO>> getSubcategories(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getSubcategories(categoryId));
    }

    @GetMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<SubcategoryResponseDTO> getSubcategoryById(
            @PathVariable Long categoryId, @PathVariable Long subId) {
        return ResponseEntity.ok(categoryService.getSubcategoryById(categoryId, subId));
    }

    @PutMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<SubcategoryResponseDTO> updateSubcategory(
            @PathVariable Long categoryId,
            @PathVariable Long subId,
            @RequestBody SubcategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.updateSubcategory(categoryId, subId, request));
    }

    @DeleteMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<Void> deleteSubcategory(
            @PathVariable Long categoryId,
            @PathVariable Long subId) {
        categoryService.deleteSubcategory(categoryId, subId);
        return ResponseEntity.noContent().build();
    }
}
