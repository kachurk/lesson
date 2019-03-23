package ru.ssu.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssu.model.Item;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Iterable<Item> findByNameContainingIgnoreCaseOrderByNameDesc(String name);

}
