package org.example.service;

import org.example.model.ShoppingItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ShoppingListService {
    private final List<ShoppingItem> items = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<ShoppingItem> getAllItems() {
        return items;
    }

    public ShoppingItem addItem(String name) {
        ShoppingItem item = new ShoppingItem(idCounter.getAndIncrement(), name, false);
        items.add(item);
        return item;
    }

    public boolean deleteItem(Long id) {
        return items.removeIf(item -> item.getId().equals(id));
    }

    public Optional<ShoppingItem> togglePurchased(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(item -> {
                    item.setPurchased(!item.isPurchased());
                    return item;
                });
    }
}