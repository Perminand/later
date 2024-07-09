package ru.practicum.shareit.item;

import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

@Data
public class Item {
    private Long id;
    private String name;
    private String description;
    private boolean available;
    private User Owner;
    private ItemRequest request;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
