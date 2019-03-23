package ru.ssu.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssu.dao.ItemRepository;
import ru.ssu.dao.UserRepository;
import ru.ssu.dto.TransactionDto;
import ru.ssu.dto.TransactionStatus;
import ru.ssu.exception.NotEnoughBalanceException;
import ru.ssu.model.Item;
import ru.ssu.model.User;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MarketEngine {

    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @Autowired
    public MarketEngine(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public MarketEngine() {
    }

    // TODO: concurrency is not handled
    // TODO: model error handling

    public TransactionDto executeTransaction(TransactionDto transactionDto) throws NotEnoughBalanceException {
        Long userId = transactionDto.getUserId();
        Long itemId = transactionDto.getItemId();

        Optional<User> persistedUser = userRepository.findById(userId);
        Optional<Item> persistedItem = itemRepository.findById(itemId);

        User user = persistedUser.get();

        BigDecimal userBalance = user.getBalance();
        BigDecimal itemPrice = persistedItem.get().getPrice();

        if (userBalance.compareTo(itemPrice) == -1) {
            throw new NotEnoughBalanceException("Not enough balance for user with id " + userId);
        }

        BigDecimal updatedPrice = userBalance.subtract(itemPrice);

        user.setBalance(updatedPrice);
        user.getItems().add(persistedItem.get());

        userRepository.save(user);

        return new TransactionDto(userId, itemId, TransactionStatus.COMPLETED);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
