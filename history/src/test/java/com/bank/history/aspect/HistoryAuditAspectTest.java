package com.bank.history.aspect;

import com.bank.history.entity.History;
import com.bank.history.entity.HistoryAudit;
import com.bank.history.service.HistoryAuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HistoryAuditAspectTest {

    @Mock
    private HistoryAuditService service;

    @InjectMocks
    private HistoryAuditAspect aspect;

    private History history;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        history = History.builder()
                .accountAuditId(1L)
                .antiFraudAuditId(2L)
                .authorizationAuditId(3L)
                .profileAuditId(4L)
                .transferAuditId(5L)
                .publicBankInfoAuditId(6L)
                .build();
    }

    @Test
    void testAfterSaveWhenHistorySavedThenAuditSaved() {
        aspect.afterSave(history);
        verify(service, times(1)).save(any(HistoryAudit.class));
    }

    @Test
    void testAfterUpdateHistoryWhenHistoryUpdatedThenAuditSaved() {
        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().getName())
                .operationType("update")
                .createdBy("User1")
                .modifiedBy("User2")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .entityJson(history.toString())
                .newEntityJson(history.toString())
                .build();

        when(service.findByEntityJsonContaining(anyString())).thenReturn(Optional.of(historyAudit));

        aspect.afterUpdateHistory(history);

        verify(service, times(1)).findByEntityJsonContaining(anyString());
        verify(service, times(1)).save(any(HistoryAudit.class));
    }

    @Test
    void testAfterDeleteHistoryWhenHistoryDeletedThenAuditSaved() {
        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().getName())
                .operationType("delete")
                .createdBy("User1")
                .modifiedBy("User2")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .entityJson(history.toString())
                .newEntityJson(history.toString())
                .build();

        when(service.findByEntityJsonContaining(anyString())).thenReturn(Optional.of(historyAudit));

        aspect.afterDeleteHistory(history);

        verify(service, times(1)).findByEntityJsonContaining(anyString());
        verify(service, times(1)).save(any(HistoryAudit.class));
    }
}