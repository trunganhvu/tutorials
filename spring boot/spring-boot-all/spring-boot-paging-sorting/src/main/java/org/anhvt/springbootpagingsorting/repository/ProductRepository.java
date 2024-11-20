package org.anhvt.springbootpagingsorting.repository;

import org.anhvt.springbootpagingsorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
