package com.example.mscategories.Controller;

import com.example.mscategories.DTO.*;
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

    // ------------------------------------------------------------
    // 🔹 Crear una categoría para un usuario
    // ------------------------------------------------------------
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    // ------------------------------------------------------------
    // 🔹 Obtener todas las categorías de un usuario
    // ------------------------------------------------------------
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.getCategoriesByUser(userId));
    }

    // ------------------------------------------------------------
    // 🔹 Obtener una categoría por su ID
    // ------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // ------------------------------------------------------------
    // 🔹 Actualizar categoría por ID
    // ------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    // ------------------------------------------------------------
    // 🔹 Eliminar categoría (y sus subcategorías automáticamente)
    // ------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------------------------------------------------
    // 🔹 Crear subcategoría dentro de una categoría
    // ------------------------------------------------------------
    @PostMapping("/{categoryId}/subcategories")
    public ResponseEntity<SubcategoryResponseDTO> createSubcategory(
            @PathVariable Long categoryId,
            @RequestBody SubcategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.createSubcategory(categoryId, request));
    }

    // ------------------------------------------------------------
    // 🔹 Obtener todas las subcategorías de una categoría
    // ------------------------------------------------------------
    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<SubcategoryResponseDTO>> getSubcategories(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getSubcategories(categoryId));
    }

    // ------------------------------------------------------------
    // 🔹 Obtener subcategoría por ID dentro de una categoría
    // ------------------------------------------------------------
    @GetMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<SubcategoryResponseDTO> getSubcategoryById(
            @PathVariable Long categoryId, @PathVariable Long subId) {
        return ResponseEntity.ok(categoryService.getSubcategoryById(categoryId, subId));
    }

    // ------------------------------------------------------------
    // 🔹 Actualizar una subcategoría
    // ------------------------------------------------------------
    @PutMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<SubcategoryResponseDTO> updateSubcategory(
            @PathVariable Long categoryId,
            @PathVariable Long subId,
            @RequestBody SubcategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.updateSubcategory(categoryId, subId, request));
    }

    // ------------------------------------------------------------
    // 🔹 Eliminar una subcategoría
    // ------------------------------------------------------------
    @DeleteMapping("/{categoryId}/subcategories/{subId}")
    public ResponseEntity<Void> deleteSubcategory(
            @PathVariable Long categoryId,
            @PathVariable Long subId) {
        categoryService.deleteSubcategory(categoryId, subId);
        return ResponseEntity.noContent().build();
    }
}
