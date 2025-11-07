package com.example.repositories;



import com.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Tu peux ajouter des requêtes personnalisées ici si besoin
}
