package ru.ssu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssu.dao.ItemRepository;
import ru.ssu.dto.ItemDto;
import ru.ssu.model.Item;

import java.util.List;
import java.util.Optional;

import static ru.ssu.dto.ItemDto.convertItems;

@RestController
@RequestMapping("/v1/items/")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<? extends ItemDto>> items() {
        Iterable<Item> persistedItems = itemRepository.findAll();

        List<ItemDto> responseItems = convertItems(persistedItems);

        return new ResponseEntity<>(responseItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<? extends ItemDto>> items(@PathVariable("name") String name) {
        Iterable<Item> persistedItems = itemRepository.findByNameContainingIgnoreCaseOrderByNameDesc(name);

        List<ItemDto> responseItems = convertItems(persistedItems);

        return new ResponseEntity<>(responseItems, HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto) {

        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Optional<Item> itemForUpdateOptional = itemRepository.findById(id);

        if (!itemForUpdateOptional.isPresent()) {

            Error error = new Error("ItemDto with id not found ...");

            return ResponseEntity.badRequest().body(error);
        }

        Item itemDtoForUpdate = itemForUpdateOptional.get();
        itemDtoForUpdate.update(itemDto);

        itemRepository.save(itemDtoForUpdate);

        return new ResponseEntity<>(new ItemDto(itemDtoForUpdate), HttpStatus.OK);
    }
}
