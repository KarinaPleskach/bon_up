package com.bsuir.diploma.bonup.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class IdToken {
    private Long id;
    private String token;
}
