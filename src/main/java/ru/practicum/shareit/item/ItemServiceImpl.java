package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.error.EntityNotFoundException;
import ru.practicum.shareit.exception.error.ValidationException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public List<Item> getItemsByUserId(long userId) {
        return itemRepository.getItems(userId);
    }

    @Override
    public Item getById(long itemId) {
        return itemRepository.getById(itemId).orElseThrow(() -> new NullPointerException("Нет item с заданным id"));
    }


    @Override
    public Item create(long userId, ItemDto item) {
        User user = userService.getById(userId);
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new ValidationException("name не может быть null");
        }
        if (item.getDescription() == null || item.getDescription().isEmpty()) {
            throw new ValidationException("description не может быть null");
        }
        item.setOwner(user);
        if(item.getAvailable() == null) {
            throw new ValidationException("available не может быть null");
        }
        Item newItem = ItemMapper.forItem(item);
        return itemRepository.create(userId, newItem);
    }

    @Override
    public Item update(Long userId, long itemId, ItemDto itemDto) {
        User user = userService.getById(userId);
        Item item = getById(itemId);
        if(!Objects.equals(item.getOwner().getId(), user.getId())) {
            throw new EntityNotFoundException("Попытка изменения item не владельцем");
        }
        if(itemDto.getName() != null) {
            item.setName(itemDto.getName());
        }
        if(itemDto.getDescription() != null) {
            item.setDescription(itemDto.getDescription());
        }
        if(itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }

        return itemRepository.update(item);
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text.isBlank()) {
            return List.of();
        } else {
            List<ItemDto> itemDtoList = itemRepository.search(text)
                    .stream()
                    .filter(Item::getAvailable)
                    .map(ItemMapper::toItemDto).toList();
            return itemDtoList;
        }
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        itemRepository.deleteByUserIdAndItemId(userId, itemId);
    }


}
