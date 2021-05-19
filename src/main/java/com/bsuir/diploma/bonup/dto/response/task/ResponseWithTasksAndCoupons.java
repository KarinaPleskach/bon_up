package com.bsuir.diploma.bonup.dto.response.task;

import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWithTasksAndCoupons {
    private List<PublicTaskNewDto> tasks;
    private List<PublicTaskNewDto> coupons;
}
