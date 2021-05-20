package com.bsuir.diploma.bonup.dto.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenIdsDro {
    private List<Long> ids;
    private String token;
}
