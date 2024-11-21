package org.anhvt.springbootjpaauditing.repository;

import org.anhvt.springbootjpaauditing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
