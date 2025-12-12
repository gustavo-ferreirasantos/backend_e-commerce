package br.com.ecommerce.controller;


import br.com.ecommerce.DTO.ProductRequestDTO;
import br.com.ecommerce.DTO.ProductResponseDTO;
import br.com.ecommerce.model.Product;
import br.com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productList= repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data){
        Product productData = new Product(data);
        repository.save(productData);
    }

}
