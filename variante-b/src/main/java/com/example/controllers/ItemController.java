package com.example.controllers;



import com.example.entities.Item;
import com.example.services.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // ✅ GET /items?page=&size=
    @GetMapping
    public Page<Item> getAll(@RequestParam(required = false) Long categoryId,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        if (categoryId != null) {
            return itemService.findByCategoryId(categoryId, page, size);
        }
        return itemService.findAll(page, size);
    }

    // ✅ GET /items/{id}
    @GetMapping("/{id}")
    public Item getById(@PathVariable Long id) {
        return itemService.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // ✅ POST /items
    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }

    // ✅ PUT /items/{id}
    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    // ✅ DELETE /items/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
