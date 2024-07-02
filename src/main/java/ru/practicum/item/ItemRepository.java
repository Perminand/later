package ru.practicum.item;

import java.util.List;

public interface ItemRepository {

    List<Item> getItems(long userId);

    Item save(long userId, Item item);

    void deleteByUserIdAndItemId(long userId, long itemId);
}
