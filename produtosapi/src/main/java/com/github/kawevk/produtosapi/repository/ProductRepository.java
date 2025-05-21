package com.github.kawevk.produtosapi.repository;

import com.github.kawevk.produtosapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByName(String name);

}
