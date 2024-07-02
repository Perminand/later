package ru.practicum.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepositoryImpl implements ItemRepository{
    private final Map<Long, Item> itemMap = new HashMap<>();
    private long generateId = 0L;

    @Override
    public List<Item> getItems(long userId) {
        return itemMap.values()
                .stream()
                .filter(item -> item.getUserId() == userId)
                .toList();
    }

    @Override
    public Item save(long userId, Item item) {
        item.setId(++generateId);
        return itemMap.put(item.getId(), item);
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        itemMap.remove(itemId);
    }
}
