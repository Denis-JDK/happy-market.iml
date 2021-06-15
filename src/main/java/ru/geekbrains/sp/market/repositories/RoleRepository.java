package ru.geekbrains.sp.market.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.sp.market.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
