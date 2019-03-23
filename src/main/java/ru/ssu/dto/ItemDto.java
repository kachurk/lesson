package ru.ssu.dto;


import ru.ssu.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public ItemDto() {
    }

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
    }


    public ItemDto(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public static List<ItemDto> convertItems(Iterable<Item> persistedItems) {
        return StreamSupport
                .stream(persistedItems.spliterator(), false)
                .map(item -> new ItemDto(item)).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDto itemDto = (ItemDto) o;

        if (id != null ? !id.equals(itemDto.id) : itemDto.id != null) return false;
        if (name != null ? !name.equals(itemDto.name) : itemDto.name != null) return false;
        if (description != null ? !description.equals(itemDto.description) : itemDto.description != null) return false;
        return price != null ? price.equals(itemDto.price) : itemDto.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
