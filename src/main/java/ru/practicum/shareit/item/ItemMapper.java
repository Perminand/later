package ru.practicum.shareit.item;


public class ItemMapper {
    public static ItemDto toItemDto (Item item) {
        return new ItemDto(item.getName(), item.getDescription(), item.getAvailable());
    }
    public static Item forItem (ItemDto itemDto) {
        return new Item(
                itemDto.getName(),
                itemDto.getDescription());

    }
}
