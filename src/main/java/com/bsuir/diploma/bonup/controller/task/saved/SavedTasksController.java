package com.bsuir.diploma.bonup.controller.task.saved;

import com.bsuir.diploma.bonup.dto.model.IdToken;
import com.bsuir.diploma.bonup.dto.model.task.employee.EmployeeResolveUserDto;
import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.TokenDto;
import com.bsuir.diploma.bonup.dto.response.ResponseWithMessage;
import com.bsuir.diploma.bonup.dto.response.task.ResponseWithTasks;
import com.bsuir.diploma.bonup.service.task.TaskService;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SavedTasksController {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private TaskService taskService;

    @PatchMapping("/{lang}/acceptTask")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> acceptTask(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        taskService.acceptTask(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PatchMapping("/{lang}/rejectTask")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> rejectTask(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        taskService.rejectTask(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfAcceptedTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfAcceptedTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(taskService.numberOfAcceptedTasks(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfRejectedTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfRejectedTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(taskService.numberOfRejectedTasks(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/acceptedTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> acceptedTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicTaskDto> tasks = taskService.acceptedTasks(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/rejectedTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> rejectedTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicTaskDto> tasks = taskService.rejectedTasks(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

    @PostMapping("/{lang}/canDoneTask")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> canDoneTask(@PathVariable("lang") String lang, @RequestBody IdToken idToken) {
        taskService.canDoneTask(idToken, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/resolveTask")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> resolveTask(@PathVariable("lang") String lang, @RequestBody EmployeeResolveUserDto employeeResolveUserDto) {
        taskService.resolveTask(employeeResolveUserDto, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/points")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> points(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(taskService.getBalls(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/numberOfDoneTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithMessage> numberOfDoneTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        String message = String.valueOf(taskService.getNumberOfDoneTasks(tokenUser, lang));
        return new ResponseEntity<>(new ResponseWithMessage(true, message), HttpStatus.OK);
    }

    @PostMapping("/{lang}/doneTasks")
    @ResponseBody
    public ResponseEntity<ResponseWithTasks> doneTasks(@PathVariable("lang") String lang, @RequestBody TokenDto tokenUser) {
        List<PublicTaskDto> tasks = taskService.getDoneTasks(tokenUser, lang);
        String message = translationService.getMessage("message.success", lang);
        return new ResponseEntity<>(new ResponseWithTasks(true, message, tasks), HttpStatus.OK);
    }

}
