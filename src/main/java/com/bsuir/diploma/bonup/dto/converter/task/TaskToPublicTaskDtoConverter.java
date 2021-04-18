package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.task.PublicTaskDto;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Task;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToPublicTaskDtoConverter implements Converter<Task, PublicTaskDto> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public PublicTaskDto convert(Task task) {
        List<Long> photos = null;
        if (Objects.nonNull(task.getPhotos())) {
            photos = task.getPhotos().stream().map(Photo::getId).collect(Collectors.toList());
        }
        return PublicTaskDto.builder()
                .id(task.getId())
                .name(task.getNameKey())
                .description(task.getDescriptionKey())
                .count(task.getCount())
                .dateFrom(format.format(task.getDateFrom().getTime()))
                .dateTo(format.format(task.getDateTo().getTime()))
                .organizationName(task.getOrganization().getName())
                .photos(photos)
                .subcategoryId(task.getCategory().getId())
                .subcategory(task.getCategory().getKey())
                .categoryId(task.getCategory().getCategory().getId())
                .category(task.getCategory().getCategory().getKey())
                .typeId(task.getType().getId())
                .type(task.getType().getKey())
                .pointsCount(task.getType().getPointsCount())
                .activity(task.getActivity())
                .accepted(null)
                .done(null)
                .rejected(null)
                .build();
    }
}
