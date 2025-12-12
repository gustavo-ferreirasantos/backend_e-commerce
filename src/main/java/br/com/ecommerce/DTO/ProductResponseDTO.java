package br.com.ecommerce.DTO;

import br.com.ecommerce.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO (UUID id, String title, BigDecimal price, String image, Long stock){
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getTitle(), product.getPrice(), product.getImage(), product.getStock());
    }

}
