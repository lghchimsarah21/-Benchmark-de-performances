

package com.example.services;

import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Page<Category> findAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public Category update(Long id, Category updated) {
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
