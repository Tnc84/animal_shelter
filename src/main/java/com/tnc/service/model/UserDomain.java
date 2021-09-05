package com.tnc.service.model;

import lombok.Data;

public record UserDomain(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
