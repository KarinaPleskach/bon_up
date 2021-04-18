package com.bsuir.diploma.bonup.dto.converter.task;

import com.bsuir.diploma.bonup.dto.model.task.stock.StockDto;
import com.bsuir.diploma.bonup.model.task.Stock;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockDtoToStockConverter implements Converter<StockDto, Stock> {

    @Override
    public Stock convert(StockDto stockDto) {

        return Stock.builder()
                .dateFrom(stockDto.getDateFrom())
                .dateTo(stockDto.getDateTo())
                .activity(stockDto.getActivity())
                .build();
    }

}
