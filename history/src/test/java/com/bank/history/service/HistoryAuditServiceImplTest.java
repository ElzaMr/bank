package com.bank.history.service;

import com.bank.history.entity.HistoryAudit;
import com.bank.history.repository.HistoryAuditRepository;
import com.bank.history.service.HistoryAuditServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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

    private static HistoryAudit historyAudit;

    @BeforeAll
    public static void setUp() {
        historyAudit = new HistoryAudit(1L, "History",
                "create",
                "User",
                "User",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "qwe", "qwe");
    }

    @BeforeEach
    public void init() {
        historyService = new HistoryAuditServiceImpl(repository);
    }

    @Nested
    @DisplayName("test showAll functionality")
    @Tag("showAll")
    class testShowAll {
        @Test
        void showAll() {
            List<HistoryAudit> histories = new ArrayList<>();
            histories.add(historyAudit);
            when(repository.findAll()).thenReturn(histories);

            List<HistoryAudit> historyDTOList = historyService.showAll();
            assertAll(
                    () -> assertNotNull(historyDTOList),
                    () -> assertEquals(historyDTOList.size(), 1),
                    () -> assertNotEquals(historyDTOList.get(0).toString(), ""),
                    () -> assertEquals(historyDTOList.get(0), historyAudit)
            );
        }
    }

    @Nested
    @DisplayName("test findById functionality")
    @Tag("findById")
    class testFindByID {
        @Test
        void serviceThrowExceptionIfDontFoundRecord() {
            when(repository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> historyService.findById(2L));
        }

        @Test
        void checkReturnType() {
            when(repository.findById(1L)).thenReturn(Optional.of(historyAudit));

            assertEquals(historyAudit, historyService.findById(1L));
        }

        @Test
        void checkThatReturnTypeNotNull() {
            when(repository.findById(1L)).thenReturn(Optional.of(historyAudit));

            assertNotNull(historyService.findById(1L));
        }
    }

    @Nested
    @DisplayName("test save functionality")
    @Tag("save")
    class testSaveByID {
        @Test
        void checkSaveMethod() {
            when(repository.save(any(HistoryAudit.class))).thenReturn(historyAudit);

            assertAll(
                    () -> assertNotNull(historyService.save(historyAudit)),
                    () -> assertEquals(historyAudit, historyService.save(historyAudit))
            );
        }
    }

    @Nested
    @DisplayName("test patch functionality")
    @Tag("patch")
    class testPatchByID {
        @Test
        void throwExceptionIfHistoryAuditNotFound() {
            when(repository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> historyService
                    .patch(1L, historyAudit));
        }

        @Test
        void checkReturnTypeAndSettingId() {
            when(repository.findById(any(Long.class))).thenReturn(Optional
                    .of(historyAudit));
            HistoryAudit newAudit = new HistoryAudit("History",
                    "create",
                    "User1",
                    "User1",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    "qwe1", "qwe1");
            newAudit.setNewEntityJson("qwe");
            when(repository.save(any(HistoryAudit.class))).thenReturn(newAudit);


            historyService.patch(2L, newAudit);//new
            assertAll(
                    () -> assertNotEquals(newAudit, historyAudit),
                    () -> assertEquals(2L, newAudit.getId())
            );
        }

        @Test
        void patchThrowExceptionIfHistoryAuditNotFound() {
            when(repository.findById(any(Long.class)))
                    .thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class,
                    () -> historyService.patch(1L, historyAudit));
        }
    }

    @Nested
    @DisplayName("test deleteById functionality")
    @Tag("deleteById")
    class testDeleteByID {
        @Test
        void deleteById() {
            when(repository.findById(1L)).thenReturn(Optional.of(historyAudit));
            when(repository.findById(2L)).thenReturn(Optional.empty());

            HistoryAudit result = historyService.deleteById(1L);
            assertAll(
                    () -> assertNotNull(result),
                    () -> assertNotEquals("", result.toString()),
                    () -> assertEquals(historyAudit, result),
                    () -> verify(repository, times(1))
                            .deleteById(any(Long.class)),
                    () -> assertThrows(NoSuchElementException.class,
                            () -> historyService.deleteById(2L))
            );
        }
    }
}