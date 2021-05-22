package com.bsuir.diploma.bonup.service.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.TokenIdsDro;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.task.FinishedTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.NewPublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.task.PublicTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.SavedTaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.employee.EmployeeResolveUserDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.task.task.TaskDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import java.util.List;

public interface TaskService {
    long create(TaskDto taskDto, String lang);

    long createTaskNew(TaskNewDto taskDto, String lang);

    void setNameAndDescription(SetNameAndDescriptionDto setNameAndDescriptionDto, String lang);

    int getNumberOfActiveTasks(TokenNameOrganization tokenNameOrganization, Type type, String lang);

    int getNumberOfActiveTasks(TokenNameOrganization tokenNameOrganization, String lang);

    int getNumberOfUnactiveTasks(TokenNameOrganization tokenNameOrganization, String lang);

    PublicTaskDto getTask(IdToken id, String lang);

    List<PublicTaskNewDto> getAllForOrg(TokenNameOrganization tokenNameOrganization, String lang);

    List<PublicTaskDto> getActiveTasks(TokenNameOrganization tokenNameOrganization, String lang);

    List<PublicTaskDto> getActiveTasks(TokenNameOrganization tokenNameOrganization, Type type, String lang);

    List<PublicTaskDto> getUnactiveTasks(TokenNameOrganization tokenNameOrganization, String lang);

    List<PublicTaskDto> getTasks(PageStockByCategoryDto pageStockByCategoryDto, String lang);

    List<NewPublicTaskDto> tasksCatalog(TokenDto tokenDto, String lang);

    void acceptTask(IdToken idToken, String lang);

    void acceptTasksNew(TokenIdsDro tokenIdsDro, String lang);

    void rejectTask(IdToken idToken, String lang);

    int numberOfAcceptedTasks(TokenDto tokenUser, String lang);

    int numberOfRejectedTasks(TokenDto tokenUser, String lang);

    List<SavedTaskNewDto> SavedTaskNewDto(TokenDto tokenDto, String lang);

    List<FinishedTaskNewDto> FinishedTaskNewDto(TokenDto tokenDto, String lang);

    List<PublicTaskDto> acceptedTasks(TokenDto tokenUser, String lang);

    List<PublicTaskDto> rejectedTasks(TokenDto tokenUser, String lang);

    void canDoneTask(IdToken idToken, String lang);

    void resolveTask(EmployeeResolveUserDto employeeResolveUserDto, String lang);

    long getBalls(TokenDto tokenUser, String lang);

    List<PublicTaskNewDto> getDoneTasks(TokenDto tokenUser, String lang);

    int getNumberOfDoneTasks(TokenDto tokenUser, String lang);
}
