package ru.ssu.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.ssu.dao.ItemRepository;
import ru.ssu.dao.UserRepository;
import ru.ssu.dto.TransactionDto;
import ru.ssu.exception.NotEnoughBalanceException;
import ru.ssu.model.Item;
import ru.ssu.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static ru.ssu.dto.TransactionStatus.COMPLETED;

@RunWith(MockitoJUnitRunner.class)
public class MarketEngineTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void testExecuteTransaction() throws NotEnoughBalanceException {

        // given
        MarketEngine marketEngine = new MarketEngine(userRepository,
                itemRepository);

        Long userId = 1l;
        Long itemId = 1l;

        TransactionDto transactionDto = new TransactionDto(userId, itemId);

        Optional<User> userOptional = Optional.of(getUser(userId));
        Optional<Item> itemOptional = Optional.of(getItem(itemId));

        Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
        Mockito.when(itemRepository.findById(itemId)).thenReturn(itemOptional);

        // when
        TransactionDto response = marketEngine.executeTransaction(transactionDto);

        // then
        Assert.assertEquals(COMPLETED, response.getTransactionStatus());
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void testExecuteTransactionFailedWithNotEnough() throws NotEnoughBalanceException {
        // given
        MarketEngine marketEngine = new MarketEngine(userRepository,
                itemRepository);

        Long userId = 1l;
        Long itemId = 1l;

        TransactionDto transactionDto = new TransactionDto(userId, itemId);

        User user = getUser(userId);
        user.setBalance(new BigDecimal(1));

        Optional<User> userOptional = Optional.of(user);
        Optional<Item> itemOptional = Optional.of(getItem(itemId));

        Mockito.when(userRepository.findById(userId)).thenReturn(userOptional);
        Mockito.when(itemRepository.findById(itemId)).thenReturn(itemOptional);

        // when
        marketEngine.executeTransaction(transactionDto);
    }

    private Item getItem(Long itemId) {
        Item item = new Item();
        item.setId(itemId);
        item.setPrice(new BigDecimal(10));

        return item;
    }

    private User getUser(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setItems(new ArrayList<>());
        user.setBalance(new BigDecimal(100));
        return user;
    }

}
