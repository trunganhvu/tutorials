package org.anhvt.springbootpdf.entity;

public record QuoteItem(
        String description,
        Integer quantity,
        Double price,
        Double total
) {
}
