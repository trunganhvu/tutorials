package org.anhvt.springbootjpaauditing.controller;

import org.anhvt.springbootjpaauditing.dto.APIResponse;
import org.anhvt.springbootjpaauditing.entity.Product;
import org.anhvt.springbootjpaauditing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    private APIResponse<List<Product>> getProducts() {
        List<Product> allProducts = service.findAllProducts();
        return new APIResponse<>(allProducts.size(), allProducts);
    }
}
