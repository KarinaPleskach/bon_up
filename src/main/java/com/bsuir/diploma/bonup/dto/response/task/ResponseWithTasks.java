package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithTasks {
    private Boolean isSuccess;
    private String message;
    private List<PublicTaskDto> tasks;
}
