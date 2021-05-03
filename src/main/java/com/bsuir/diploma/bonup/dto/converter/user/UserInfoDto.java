package com.bsuir.diploma.bonup.dto.converter.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private String name;
    private String login;
    private String organizationName;

    private Long balls;
    private Long tasksNumber;
    private Long spentBalls;

    private Long donePercent;
    private Long couponsPercent;

    private Long ballsPercent;
    private Long spentBallsPercent;
}
