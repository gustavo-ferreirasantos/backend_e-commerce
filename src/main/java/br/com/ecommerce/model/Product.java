package br.com.ecommerce.model;


import br.com.ecommerce.DTO.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private BigDecimal price;
    private String image;
    private Long stock;

    public Product(ProductRequestDTO data){
        this.title = data.title();
        this.price = data.price();
        this.image = data.image();
        this.stock = data.stock();
    }

}
