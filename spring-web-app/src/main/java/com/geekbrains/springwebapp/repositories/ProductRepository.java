package com.geekbrains.springwebapp.repositories;

import com.geekbrains.springwebapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
