package ru.geekbrains.sp.market.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.sp.market.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
