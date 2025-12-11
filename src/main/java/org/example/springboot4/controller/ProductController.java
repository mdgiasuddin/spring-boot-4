package org.example.springboot4.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.entity.Product;
import org.example.springboot4.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repository;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return repository.findAll();
    }
}
