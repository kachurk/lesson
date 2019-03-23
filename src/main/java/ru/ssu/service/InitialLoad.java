package ru.ssu.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssu.dao.ItemRepository;
import ru.ssu.dao.UserRepository;
import ru.ssu.model.Item;
import ru.ssu.model.User;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InitialLoad {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialLoad.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;


    @PostConstruct
    public void initialLoad() {
        LOGGER.info("Started initial load for market service ... {}", new Date());

        Item persistedItem = itemRepository.save(
                new Item("Stuff", "Add +1 to power ...", new BigDecimal(10)));

        itemRepository.save(new Item("Shield", "Add +1 to defends ...", new BigDecimal(7)));
        itemRepository.save(new Item("Head", "Add +1 to defends and +1 to power ...",
                new BigDecimal(7)));

        itemRepository.save(new Item("SuperHead", "Add +2 to defends and +1 to power ...",
                new BigDecimal(7)));


        User user = makeUser(persistedItem);

        userRepository.save(user);

        LOGGER.info("Finished initial load for market service ... {}", new Date());
    }

    private User makeUser(Item persistedItem) {
        User user = new User();

        user.setBalance(new BigDecimal(100));
        user.setFullName("Вася Пупкин");

        List<Item> items = new ArrayList<>();
        items.add(persistedItem);

        user.setItems(items);
        return user;
    }
}
