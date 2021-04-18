package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseWithTask {
    private Boolean isSuccess;
    private String message;
    private PublicTaskDto task;
}
