package com.bank.history.mapper;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Маппер
 * конвертирует History в HistoryDTO
 * инжектится при помощи спринга
 */
@Mapper(uses = HistoryMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface HistoryMapper {

    HistoryDTO toDTO(History history);
}
