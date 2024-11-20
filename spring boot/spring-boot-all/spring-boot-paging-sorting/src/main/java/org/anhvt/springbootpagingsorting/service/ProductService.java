package org.anhvt.springbootpagingsorting.service;

import org.anhvt.springbootpagingsorting.entity.Product;
import org.anhvt.springbootpagingsorting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAllProducts() {
        return repository.findAll();
    }


    public List<Product> findProductsWithSorting(String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        return repository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        return repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }

}
