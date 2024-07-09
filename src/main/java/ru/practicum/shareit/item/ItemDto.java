package ru.practicum.shareit.item;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

@Data
@AllArgsConstructor
public class ItemDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    private Boolean available;
    private User Owner;
    private ItemRequest request;

    public ItemDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

