package com.bank.history.service;


import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {

    @Mock
    private HistoryRepository repository;

    @Mock
    private HistoryMapper historyMapper;

    @InjectMocks
    private HistoryServiceImpl historyService;

    private History history;
    private HistoryDTO historyDTO;

    @BeforeEach//перед каждым тестом
    void setUp() {
        history = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);
        historyDTO = new HistoryDTO(1L, 1L, 1L, 1L, 1L, 1L);
    }

    @Test
    void showAll() {
        List<History> list = new ArrayList();
        list.add(history);
        when(repository.findAll()).thenReturn(list);//возвращает лист историй
        when(historyMapper.toDTO(history)).thenReturn(historyDTO);//возвращаем хисториДТО

        List<HistoryDTO> result = historyService.showAll();
        result.forEach(System.out::println);

        assertEquals(1, result.size());
        assertEquals(historyDTO, result.get(0));
    }

    @Test
    void findById() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(history));
        when(historyMapper.toDTO(history)).thenReturn(historyDTO);

        HistoryDTO result = historyService.findById(1L);

        assertEquals(historyDTO, result);
    }

    @Test
    void save() {
        when(repository.save(any(History.class))).thenReturn(history);
        when(historyMapper.toDTO(history)).thenReturn(historyDTO);

        HistoryDTO result = historyService.save(history);

        assertEquals(historyDTO, result);
    }

    @Test
    void patch() {
        when(repository.save(any(History.class))).thenReturn(history);
        when(historyMapper.toDTO(history)).thenReturn(historyDTO);

        HistoryDTO result = historyService.patch(1L, history);

        assertEquals(historyDTO, result);
    }

    @Test
    void deleteById() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(history));
        when(historyMapper.toDTO(history)).thenReturn(historyDTO);

        HistoryDTO result = historyService.deleteById(1L);

        assertEquals(historyDTO, result);
        verify(repository, times(1)).deleteById(any(Long.class));
    }

    @Test
    void getById() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(history));

        History result = historyService.getById(1L);
        assertEquals(history, result);
    }
}