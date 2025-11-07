package com.example.services;

import com.example.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final EntityManager entityManager;

    public CategoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Sauvegarde ou mise à jour d'une catégorie
    public Category save(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
            return category;
        } else {
            return entityManager.merge(category);
        }
    }

    // Recherche d'une catégorie par ID
    public Optional<Category> findById(Long id) {
        // Ici on peut charger les items si nécessaire
        TypedQuery<Category> q = entityManager.createQuery(
                "SELECT c FROM Category c LEFT JOIN FETCH c.items WHERE c.id = :id", Category.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findFirst();
    }

    // Suppression d'une catégorie
    public void delete(Category category) {
        Category managed = entityManager.contains(category) ? category : entityManager.merge(category);
        entityManager.remove(managed);
    }

    // Récupère toutes les catégories avec pagination
    public List<Category> findAll(int page, int size) {
        TypedQuery<Category> q = entityManager.createQuery(
                "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.items ORDER BY c.id",
                Category.class
        );
        q.setFirstResult(page * size);
        q.setMaxResults(size);
        return q.getResultList();
    }

    // Compte le nombre total de catégories
    public long countAll() {
        return entityManager.createQuery("SELECT COUNT(c) FROM Category c", Long.class).getSingleResult();
    }
}
