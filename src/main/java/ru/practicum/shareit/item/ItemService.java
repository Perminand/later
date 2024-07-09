package ru.practicum.shareit.item;

import java.util.List;

public interface ItemService {
    List<ItemDto> getItemsByUserId(long userId);

    ItemDto getById(long itemId);

    Item create(long userId, ItemDto item);

    void deleteItem(long userId, long itemId);

    Item update(Long userId, long itemId, ItemDto itemDto);

    List<ItemDto> search(Long userId, String text);
}
