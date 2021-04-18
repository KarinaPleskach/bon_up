package com.bsuir.diploma.bonup.dto.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TokenNameOrganization {
    private String token;
    private String name;
}