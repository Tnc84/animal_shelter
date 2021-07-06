package com.tnc.service.model;

public record UserDomain(
        Long id,
        String name,
        String address,
        String email,
        String phone
) {
}
