package com.bsuir.diploma.bonup.controller.task;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.organization.TokenNameOrganization;
import com.bsuir.diploma.bonup.dto.model.photo.IdPhotoDto;
import com.bsuir.diploma.bonup.dto.model.task.TaskNewDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.PageStockByCategoryDto;
import com.bsuir.diploma.bonup.dto.model.task.stock.SetNameAndDescriptionDto;
import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.task.task.TaskDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithTask;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithTasks;
import com.bsuir.diploma.bonup.model.task.additional.Type;
import com.bsuir.diploma.bonup.service.photo.task.TaskPhotoService;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.task.additional.TypeService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TaskPhotoService taskPhotoService;

    @PutMapping("/{lang}/task")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createTask(@PathVariable("lang") String lang, @RequestBody TaskDto taskDto) {
        String message = String.valueOf(taskService.create(taskDto, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/newTask")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> createNewTask(@PathVariable("lang") String lang, @RequestBody TaskNewDto taskDto) {
        String message = String.valueOf(taskService.createTaskNew(taskDto, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/setTaskNameAndDescription")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> setTaskNameAndDescription(@PathVariable("lang") String lang, @RequestBody SetNameAndDescriptionDto setNameAndDescriptionDto) {
        taskService.setNameAndDescription(setNameAndDescriptionDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PutMapping("/{lang}/taskPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> saveTaskPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        taskPhotoService.saveTaskPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.setted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @DeleteMapping("/{lang}/taskPhoto")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> deleteTaskPhoto(@PathVariable("lang") String lang, @RequestBody IdPhotoDto idPhotoDto) {
        taskPhotoService.deleteTaskPhoto(idPhotoDto, lang);
        String message = translationService.getMessage("message.deleted", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveHeavyTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveHeavyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(1L, lang);
        String message = String.valueOf(taskService.getNumberOfActiveTasks(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveMediumTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveMediumTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(2L, lang);
        String message = String.valueOf(taskService.getNumberOfActiveTasks(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveEasyTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveEasyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(3L, lang);
        String message = String.valueOf(taskService.getNumberOfActiveTasks(tokenNameOrganization, type, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfActiveTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfActiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(taskService.getNumberOfActiveTasks(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfUnactiveTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfUnactiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        String message = String.valueOf(taskService.getNumberOfUnactiveTasks(tokenNameOrganization, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/task")
    @ResponseBody
    public ResponseEntity<ResponseWithTask> getTask(@PathVariable("lang") String lang, @RequestBody IdToken id) {
        PublicTaskDto task = taskService.getTask(id, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTask(true, message, task), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> activeTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicTaskDto> tasks = taskService.getActiveTasks(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/unactiveTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> unactiveTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        List<PublicTaskDto> tasks = taskService.getUnactiveTasks(tokenNameOrganization, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeEasyTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> activeEasyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(3L, lang);
        List<PublicTaskDto> tasks = taskService.getActiveTasks(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeMediumTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> activeMediumTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(2L, lang);
        List<PublicTaskDto> tasks = taskService.getActiveTasks(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/activeHeavyTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> activeHeavyTasks(@PathVariable("lang") String lang, @RequestBody TokenNameOrganization tokenNameOrganization) {
        Type type = typeService.getById(1L, lang);
        List<PublicTaskDto> tasks = taskService.getActiveTasks(tokenNameOrganization, type, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/tasks")
    @ResponseBody
    public List<PublicTaskDto> getTasks(@PathVariable("lang") String lang, @RequestBody PageStockByCategoryDto pageStockByCategoryDto) {
        List<PublicTaskDto> tasks = taskService.getTasks(pageStockByCategoryDto, lang);
        return tasks;
    }

}
