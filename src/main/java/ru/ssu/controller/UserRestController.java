package ru.ssu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ssu.dao.UserRepository;
import ru.ssu.dto.UserDto;
import ru.ssu.model.User;

import java.util.List;

import static ru.ssu.dto.UserDto.convertUsers;

@RestController
@RequestMapping("/v1/users/")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<?>> users() {
        Iterable<User> persistedItems = userRepository.findAll();

        List<UserDto> responseUsers = convertUsers(persistedItems);

        return new ResponseEntity<>(responseUsers, HttpStatus.OK);
    }
}
