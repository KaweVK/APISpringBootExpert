package com.github.kawevk.produtosapi.controller;

import com.github.kawevk.produtosapi.model.Product;
import com.github.kawevk.produtosapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(
//            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_XML_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Product create(@RequestBody Product product) {
        System.out.println(product);
        productRepository.save(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") String id, @RequestBody Product product) {
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam("name") String name) {
        return productRepository.findByName(name);
    }

}
