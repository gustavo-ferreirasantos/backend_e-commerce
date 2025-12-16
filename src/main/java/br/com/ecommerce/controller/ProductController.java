package br.com.ecommerce.controller;


import br.com.ecommerce.DTO.ProductRequestDTO;
import br.com.ecommerce.DTO.ProductResponseDTO;
import br.com.ecommerce.model.Product;
import br.com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado"
                ));

        return new ProductResponseDTO(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody ProductRequestDTO data){
        Product productData = new Product(data);
        repository.save(productData);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable UUID id, @RequestBody ProductRequestDTO data){
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
                ));

        product.setTitle(data.title());
        product.setPrice(data.price());
        product.setImage(data.image());
        product.setStock(data.stock());
        repository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Produto não encontrado"
            );
        }
        repository.deleteById(id);
    }


}
