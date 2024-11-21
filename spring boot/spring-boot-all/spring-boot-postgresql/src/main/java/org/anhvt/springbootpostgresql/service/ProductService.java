package org.anhvt.springbootpostgresql.service;

import org.anhvt.springbootpostgresql.entity.Product;
import org.anhvt.springbootpostgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

}
