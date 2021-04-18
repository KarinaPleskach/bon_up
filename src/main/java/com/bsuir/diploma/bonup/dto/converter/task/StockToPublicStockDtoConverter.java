package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.stock.PublicStockDto;
import com.bsuir.diploma.bonup.model.photo.Photo;
import com.bsuir.diploma.bonup.model.task.Stock;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockToPublicStockDtoConverter implements Converter<Stock, PublicStockDto> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public PublicStockDto convert(Stock stock) {
        List<Long> photos = null;
        if (Objects.nonNull(stock.getPhotos())) {
            photos = stock.getPhotos().stream().map(Photo::getId).collect(Collectors.toList());
        }
        return PublicStockDto.builder()
                .id(stock.getId())
                .dateFrom(format.format(stock.getDateFrom().getTime()))
                .dateTo(format.format(stock.getDateTo().getTime()))
                .organizationName(stock.getOrganization().getName())
                .photos(photos)
                .subcategoryId(stock.getCategory().getId())
                .subcategory(stock.getCategory().getKey())
                .categoryId(stock.getCategory().getCategory().getId())
                .category(stock.getCategory().getCategory().getKey())
                .name(stock.getNameKey())
                .description(stock.getDescriptionKey())
                .activity(stock.getActivity())
                .beloved(null)
                .build();
    }
}
