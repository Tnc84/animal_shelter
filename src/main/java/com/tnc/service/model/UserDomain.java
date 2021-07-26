package com.tnc.service.model;

public record UserDomain(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
