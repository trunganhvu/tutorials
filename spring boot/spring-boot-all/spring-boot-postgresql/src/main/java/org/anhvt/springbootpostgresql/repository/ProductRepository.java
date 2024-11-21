package org.anhvt.springbootpostgresql.repository;

import org.anhvt.springbootpostgresql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
