package ru.ssu.dto;


import java.math.BigDecimal;

public class ExtraItemDtoDto extends ItemDto {

    private final BigDecimal EXTRA_PRICE = new BigDecimal(1.20);

    public ExtraItemDtoDto(Long id,
                           String name,
                           String description,
                           BigDecimal price) {
        super(id, name, description, price);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().multiply(EXTRA_PRICE).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
