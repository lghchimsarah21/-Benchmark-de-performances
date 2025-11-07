
package com.example.controllers;

import com.example.entities.Category;
import com.example.entities.Item;
import com.example.services.CategoryService;
import com.example.services.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public CategoryController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    // ✅ GET /categories?page=&size=
    @GetMapping
    public List<Category> getCategories(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return categoryService.findAll(page, size).getContent();
    }



    // ✅ GET /categories/{id}
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // ✅ POST /categories
    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.save(category);
    }

    // ✅ PUT /categories/{id}
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    // ✅ DELETE /categories/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    // ✅ GET /categories/{id}/items?page=&size= (relationnelle)
    @GetMapping("/{id}/items")
    public Page<Item> getItemsByCategory(@PathVariable Long id,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return itemService.findByCategoryId(id, page, size);
    }
}
