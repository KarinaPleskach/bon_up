package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.task.TaskDto;
import com.bsuir.diploma.bonup.model.task.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoToTaskConverter implements Converter<TaskDto, Task> {

    @Override
    public Task convert(TaskDto taskDto) {
        return Task.builder()
                .nameKey(taskDto.getName())
                .descriptionKey(taskDto.getDescription())
                .dateFrom(taskDto.getDateFrom())
                .dateTo(taskDto.getDateTo())
                .count(taskDto.getCount())
                .activity(taskDto.getActivity())
                .build();
    }
}
