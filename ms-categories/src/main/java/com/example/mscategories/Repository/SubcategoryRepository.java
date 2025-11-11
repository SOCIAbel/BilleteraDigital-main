package com.example.mscategories.Repository;

import com.example.mscategories.Entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findByCategoryId(Long categoryId);

    // ✅ Método para eliminar todas las subcategorías de una categoría
    @Transactional
    void deleteAllByCategoryId(Long categoryId);
}
