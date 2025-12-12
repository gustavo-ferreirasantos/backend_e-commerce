package br.com.ecommerce.DTO;

import java.math.BigDecimal;

public record ProductRequestDTO(String title, BigDecimal price, String image, Long stock) {

}
