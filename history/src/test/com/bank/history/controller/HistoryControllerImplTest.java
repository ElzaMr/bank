package com.bank.history.controller;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.service.HistoryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryControllerImplTest {
    @Mock
    private HistoryServiceImpl service;
    @Mock
    private HistoryMapper mapper;
    @InjectMocks
    private HistoryControllerImpl historyController;
    private static History history;
    private static HistoryDTO dto;


    @BeforeAll
    public static void setUp() {
        history = new History(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        dto = new HistoryDTO(2L, 3L, 4L, 5L, 6L, 7L);
    }


    @Test
    void showAll() {
        List<History> listOfHistories = List.of(history);
        when(service.showAll()).thenReturn(listOfHistories);
        List<HistoryDTO> listDTO = List.of(dto);
        when(mapper.toDTO(history)).thenReturn(dto);

        assertAll(
                (() -> assertNotNull(historyController.showAll())),
                () -> assertEquals(ResponseEntity.of(Optional.of(listDTO)), historyController.showAll())
        );

    }

    @Test
    void showOne() {
        when(service.findById(any(Long.class))).thenReturn(history);
        when(mapper.toDTO(history)).thenReturn(dto);

        assertAll(
                () -> assertNotNull(historyController.showOne(1L)),
                () -> assertEquals(ResponseEntity.ok(dto), historyController.showOne(1L))
        );


    }

    @Test
    void save() {
        when(service.save(history)).thenReturn(history);
        when(mapper.toDTO(history)).thenReturn(dto);

        assertAll(
                () -> assertEquals(ResponseEntity.of(Optional.of(dto)), historyController.save(history)),
                () -> assertNotNull(historyController.save(history))
        );
    }

    @Test
    void patch() {
        when(service.patch(any(Long.class), any(History.class))).thenReturn(history);
        when(mapper.toDTO(history)).thenReturn(dto);

        assertAll(
                () -> assertEquals(ResponseEntity.of(Optional.of(dto)), historyController.patch(1L, history)),
                () -> assertNotNull(historyController.patch(1L, history))
        );
    }

    @Test
    void delete() {
        when(service.deleteById(any(Long.class))).thenReturn(history);
        when(mapper.toDTO(history)).thenReturn(dto);

        assertAll(
                () -> assertNotNull(historyController.delete(1L)),
                () -> assertEquals(ResponseEntity.of(Optional.of(dto)), historyController.delete(1L))
        );
    }
}