package com.bsuir.diploma.bonup.dto.converter.user;

import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskWithTriggerDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UserInfoDto {
    private String name;
    private String email;
    private String organizationName;

    private Long balls;
    private Long tasksNumber;
    private Long spentBalls;

    private List<TaskWithTriggerDto> finishedTasks;
    private List<TaskWithTriggerDto> finishedCoupons;

    List<Goal> goals = new ArrayList<>();

    private Long photoId;

//    private Long donePercent;
//    private Long couponsPercent;
//
//    private Long ballsPercent;
//    private Long spentBallsPercent;
}
