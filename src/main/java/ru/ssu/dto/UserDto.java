package ru.ssu.dto;


import ru.ssu.model.Item;
import ru.ssu.model.User;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.ssu.dto.ItemDto.convertItems;

public class UserDto {

    private Long id;
    private String fullName;
    private BigDecimal balance;

    private List<ItemDto> items;


    public UserDto(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.balance = user.getBalance();
        this.items = convertItems(user.getItems());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public static List<UserDto> convertUsers(Iterable<User> persistedItems) {
        return StreamSupport
                .stream(persistedItems.spliterator(), false)
                .map(user -> new UserDto(user)).collect(Collectors.toList());
    }
}
