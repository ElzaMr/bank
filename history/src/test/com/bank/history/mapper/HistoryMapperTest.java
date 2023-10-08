package com.bank.history.mapper;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HistoryMapperTest {
    private History history;
    private HistoryDTO historyDTO;
    private HistoryMapper historyMapper;

    @BeforeEach
    void setUp() {
        history = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);
        historyDTO = new HistoryDTO(1L, 1L, 1L, 1L, 1L, 1L);
        historyMapper = new HistoryMapperImpl();

    }

    @Test
    void check() {
        HistoryDTO result = historyMapper.toDTO(history);

        assertEquals(result, historyDTO);
    }
}