package org.example.controller;

import org.example.model.ShoppingItem;
import org.example.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ShoppingItemController {

    @Autowired
    private ShoppingListService service;

    // 1. Просматривать список покупок
    @GetMapping
    public List<ShoppingItem> getAllItems() {
        return service.getAllItems();
    }

    // 2. Добавлять элемент в список
    @PostMapping
    public ShoppingItem addItem(@RequestBody ShoppingItem request) {
        return service.addItem(request.getName());
    }

    // 3. Удалять элемент из списка
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (service.deleteItem(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 4. Отмечать элемент купленным
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<ShoppingItem> togglePurchased(@PathVariable Long id) {
        return service.togglePurchased(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}