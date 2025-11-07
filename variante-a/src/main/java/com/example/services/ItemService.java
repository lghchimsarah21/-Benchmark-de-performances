package com.example.services;

import com.example.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ItemService {

    private final EntityManager entityManager;

    public ItemService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Sauvegarder ou mettre à jour un Item
    public Item save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return item;
        } else {
            return entityManager.merge(item);
        }
    }

    // Rechercher un item par ID
    public Optional<Item> findById(Long id) {
        TypedQuery<Item> q = entityManager.createQuery(
                "SELECT i FROM Item i LEFT JOIN FETCH i.category WHERE i.id = :id",
                Item.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findFirst();
    }

    // Supprimer un Item
    public void delete(Item item) {
        Item managed = entityManager.contains(item) ? item : entityManager.merge(item);
        entityManager.remove(managed);
    }

    // Liste paginée
    public List<Item> findAll(int page, int size) {
        TypedQuery<Item> q = entityManager.createQuery(
                "SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.category ORDER BY i.id",
                Item.class
        );
        q.setFirstResult(page * size);
        q.setMaxResults(size);
        return q.getResultList();
    }

    // Compter les items
    public long countAll() {
        return entityManager.createQuery("SELECT COUNT(i) FROM Item i", Long.class)
                .getSingleResult();
    }
}
