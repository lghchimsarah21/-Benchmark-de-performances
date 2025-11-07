package com.example.repositories;

import com.example.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // ✅ Filtrer les items par ID de catégorie avec pagination
    Page<Item> findByCategoryId(Long categoryId, Pageable pageable);
}
