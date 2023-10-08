package com.bank.history.service;

import com.bank.history.entity.HistoryAudit;
import com.bank.history.repository.HistoryAuditRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HistoryAuditServiceImplTest {

    @Mock
    private HistoryAuditRepository repository;

    @InjectMocks
    private HistoryAuditServiceImpl historyService;

    private static HistoryAudit historyTest;

    @BeforeAll
    public static void setUp() {
        historyTest = new HistoryAudit();
    }

    @BeforeEach
    public void init() {
        historyService = new HistoryAuditServiceImpl(repository);
    }

    @Test
    void showAll() {
        List<HistoryAudit> histories = new ArrayList<>();
        histories.add(historyTest);
        when(repository.findAll()).thenReturn(histories);

        List<HistoryAudit> historyDTOList = historyService.showAll();
        assertAll(
                () -> assertNotNull(historyDTOList),
                () -> assertEquals(historyDTOList.size(), 1),
                () -> assertNotEquals(historyDTOList.get(0).toString(), ""),
                () -> assertEquals(historyDTOList.get(0), historyTest)
        );
    }

    @Test
    void serviceThrowExceptionIfDontFoundRecord() {//todo
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> historyService.findById(2L));
    }

    @Test
    void checkReturnType() {
        when(repository.findById(1L)).thenReturn(Optional.of(historyTest));

        assertEquals(historyTest, historyService.findById(1L));
    }

    @Test
    void checkThatReturnTypeNotNull() {
        when(repository.findById(1L)).thenReturn(Optional.of(historyTest));

        assertNotNull(historyService.findById(1L));
    }

    @Test
    void checkSaveMethod() {
        when(repository.save(any(HistoryAudit.class))).thenReturn(historyTest);

        assertAll(
                () -> assertNotNull(historyService.save(historyTest)),
                () -> assertEquals(historyTest, historyService.save(historyTest))
        );
    }

    @Test
    void historySetId() {//todo
        when(repository.save(any(HistoryAudit.class))).thenReturn(historyTest);
        when(repository.findById(2L)).thenReturn(Optional.of(historyTest));

        historyService.patch(2L, historyTest);
        assertAll(
                () -> assertEquals(2L, historyTest.getId())
//                () -> assertThrows(NotFoundException.class, () -> historyService.patch(1L, historyTest)
                );
    }

    @Test
    void deleteById() {
        when(repository.findById(1L)).thenReturn(Optional.of(historyTest));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        HistoryAudit result = historyService.deleteById(1L);
        assertAll(
                () -> assertNotNull(result),
                () -> assertNotEquals("", result.toString()),
                () -> assertEquals(historyTest, result),
                () -> verify(repository, times(1)).deleteById(any(Long.class)),
                () -> assertThrows(NoSuchElementException.class, () -> historyService.deleteById(2L))
        );
    }
}