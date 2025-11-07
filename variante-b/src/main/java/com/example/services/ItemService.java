
package com.example.services;

import com.example.entities.Item;
import com.example.repositories.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public Page<Item> findAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Optional<Item> findById(Long id) {
        return repo.findById(id);
    }

    public Page<Item> findByCategoryId(Long categoryId, int page, int size) {
        return repo.findByCategoryId(categoryId, PageRequest.of(page, size));
    }

    public Item save(Item item) {
        return repo.save(item);
    }

    public Item update(Long id, Item updated) {
        return repo.findById(id).map(existing -> {
            existing.setLabel(updated.getLabel());
            existing.setPrice(updated.getPrice());
            existing.setCategory(updated.getCategory());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
