package com.bank.history.service;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;

public interface HistoryService extends HistorySuperService<History> {
    History getById(Long id);
}
