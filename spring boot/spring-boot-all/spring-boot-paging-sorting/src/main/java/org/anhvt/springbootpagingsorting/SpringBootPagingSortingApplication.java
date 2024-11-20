package org.anhvt.springbootpagingsorting;

import org.anhvt.springbootpagingsorting.entity.Product;
import org.anhvt.springbootpagingsorting.repository.ProductRepository;
import org.anhvt.springbootpagingsorting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootPagingSortingApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPagingSortingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> new Product("Product" + i,
                        new Random().nextInt(100), new Random().nextInt(50000)))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }
}
