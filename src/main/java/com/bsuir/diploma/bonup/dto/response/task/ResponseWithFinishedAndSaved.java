package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.FinishedTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.SavedTaskNewDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWithFinishedAndSaved {
    private Boolean isSuccess;
    private String message;
    List<SavedTaskNewDto> saved;
    List<FinishedTaskNewDto> finished;
}
