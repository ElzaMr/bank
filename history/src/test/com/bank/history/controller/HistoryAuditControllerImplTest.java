package com.bank.history.controller;

import com.bank.history.entity.HistoryAudit;
import com.bank.history.service.HistoryAuditService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryAuditControllerImplTest {
    @Mock
    HistoryAuditService service;
    @InjectMocks
    HistoryAuditControllerImpl historyController;
    private static final LocalDateTime localDateTime = LocalDateTime.now();
    static HistoryAudit audit;
    static List<HistoryAudit> listOfAudits;

    @BeforeAll
    public static void setUp() {
        audit = new HistoryAudit(1L, "History", "create", "User", "User1", localDateTime, localDateTime, "qwe", "qwe");
        listOfAudits = List.of(audit);
    }

    @Test
    void showAll() {
        when(service.showAll()).thenReturn(listOfAudits);

        ResponseEntity<List<HistoryAudit>> responseEntity = historyController.showAll();
        assertAll(
                () -> assertEquals(Objects.requireNonNull(responseEntity.getBody()).size(), 1),
                () -> assertEquals(Objects.requireNonNull(responseEntity.getBody()).get(0), audit)
        );
    }

    @Test
    void showOne() {
        when(service.findById(any(Long.class))).thenReturn(audit);

        ResponseEntity<HistoryAudit> result = historyController.showOne(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getBody(), audit)
        );
    }

    @Test
    void save() {
        when(service.save(any(HistoryAudit.class))).thenReturn(audit);

        ResponseEntity<HistoryAudit> result = historyController.save(audit);

        assertEquals(result.getBody(), audit);
    }

    @Test
    void patch() {
        audit.setId(2L);
        when(service.patch(any(Long.class), any(HistoryAudit.class))).thenReturn(audit);

        ResponseEntity<HistoryAudit> result = historyController.patch(2L, audit);

        assertAll(
                () -> assertNotNull(result.getBody()),
                () -> assertEquals(result.getBody(), audit),
                () -> assertEquals(Objects.requireNonNull(result.getBody()).getId(), 2L)
        );
    }

    @Test
    void delete() {
        when(service.deleteById(any(Long.class))).thenReturn(audit);

        ResponseEntity<HistoryAudit> result = historyController.delete(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(Objects.requireNonNull(result.getBody()).getId(), 1L),
                () -> assertEquals(result.getBody(), audit)
        );
    }
}