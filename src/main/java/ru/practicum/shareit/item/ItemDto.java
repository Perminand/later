package ru.practicum.shareit.item;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    private Boolean available;
}

