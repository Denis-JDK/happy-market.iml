package com.geekbrains.springwebapp.services;


import com.geekbrains.springwebapp.entities.Product;
import com.geekbrains.springwebapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class ProductService {
        private ProductRepository productRepository;
        @Autowired
        public void setProductRepository(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public Product getProductById(Long id){
           return productRepository.getOne(id);
        }

        public List<Product> getAllProducts () {
            return productRepository.findAll();

    }
        public void deleteProductById(Long id){
            productRepository.deleteById(id);
    }


}
