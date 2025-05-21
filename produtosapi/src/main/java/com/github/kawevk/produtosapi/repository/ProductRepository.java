package com.github.kawevk.produtosapi.repository;

import com.github.kawevk.produtosapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
