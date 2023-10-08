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
    private static HistoryAudit audit;
    private static List<HistoryAudit> listOfAudits;
    private ResponseEntity<HistoryAudit> result;

    @BeforeAll
    public static void setUp() {
        audit = HistoryAudit
                .builder().id(1L).entityType("History").operationType("create")
                .createdBy("User").modifiedBy("User1").newEntityJson("qwe")
                .entityJson("qwe").build();
        listOfAudits = List.of(audit);
    }

    @Test
    void showAll() {
        when(service.showAll()).thenReturn(listOfAudits);

        ResponseEntity<List<HistoryAudit>> responseEntity = historyController.showAll();
        assertAll(
                () -> assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size()),
                () -> assertEquals(audit, Objects.requireNonNull(responseEntity.getBody()).get(0))
        );
    }

    @Test
    void showOne() {
        when(service.findById(any(Long.class))).thenReturn(audit);

        result = historyController.showOne(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(audit, result.getBody())
        );
    }

    @Test
    void save() {
        when(service.save(any(HistoryAudit.class))).thenReturn(audit);

        result = historyController.save(audit);

        assertEquals(audit, result.getBody());
    }

    @Test
    void patch() {
        audit.setId(2L);
        when(service.patch(any(Long.class), any(HistoryAudit.class))).thenReturn(audit);

        result = historyController.patch(2L, audit);

        assertAll(
                () -> assertNotNull(result.getBody()),
                () -> assertEquals(audit, result.getBody()),
                () -> assertEquals(2L, Objects.requireNonNull(result.getBody()).getId())
        );
    }

    @Test
    void delete() {
        when(service.deleteById(any(Long.class))).thenReturn(audit);

        result = historyController.delete(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, Objects.requireNonNull(result.getBody()).getId()),
                () -> assertEquals(audit, result.getBody())
        );
    }
}