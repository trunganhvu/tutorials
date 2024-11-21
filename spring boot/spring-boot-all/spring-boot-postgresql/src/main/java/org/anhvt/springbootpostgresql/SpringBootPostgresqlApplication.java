package org.anhvt.springbootpostgresql;

import org.anhvt.springbootpostgresql.entity.Product;
import org.anhvt.springbootpostgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootPostgresqlApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPostgresqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new Product("Product" + i,
                        new Random().nextInt(100), new Random().nextInt(50000)))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }
}
