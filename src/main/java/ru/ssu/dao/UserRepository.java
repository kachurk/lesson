package ru.ssu.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssu.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
