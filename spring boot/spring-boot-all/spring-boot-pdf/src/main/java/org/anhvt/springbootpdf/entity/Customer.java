package org.anhvt.springbootpdf.entity;

public record Customer(
        String companyName,
        String contactName,
        String address,
        String email,
        String phone
) {
}
