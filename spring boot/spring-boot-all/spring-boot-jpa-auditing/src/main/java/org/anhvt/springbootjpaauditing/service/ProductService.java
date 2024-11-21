package org.anhvt.springbootjpaauditing.service;

import org.anhvt.springbootjpaauditing.entity.Product;
import org.anhvt.springbootjpaauditing.repository.ProductRepository;
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
