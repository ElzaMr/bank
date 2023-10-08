package com.bank.history.service;


import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.repository.HistoryRepository;
import org.junit.jupiter.api.*;
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
class HistoryServiceImplTest {

    @Mock
    private HistoryRepository repository;

    @InjectMocks
    private HistoryServiceImpl historyService;

    private static History historyTest;
    private static HistoryDTO historyDTOTest;

    @BeforeAll
    public static void setUp() {
        historyTest = new History(2L,
                3L, 4L, 5L,
                6L, 7L);
    }

    @BeforeEach
    public void init() {
        historyService = new HistoryServiceImpl(repository);
    }

    @Test
    void showAll() {
        List<History> histories = new ArrayList<>();
        histories.add(historyTest);
        when(repository.findAll()).thenReturn(histories);

        List<History> historyDTOList = historyService.showAll();
        assertAll(
                () -> assertNotNull(historyDTOList),
                () -> assertEquals(historyDTOList.size(), 1),
                () -> assertNotEquals(historyDTOList.get(0).toString(), ""),
                () -> assertEquals(historyDTOList.get(0), historyTest)
        );
    }

    @Nested
    @DisplayName("test findById functionality")
    @Tag("findById")
    class testFindById {
        @Test
        void serviceThrowExceptionIfDontFoundRecord() {
            when(repository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> historyService.findById(2L));
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
    }

    @Nested
    @DisplayName("test Save functionality")
    @Tag("Save")
    class testSaveById {
        @Test
        void checkSaveMethod() {
            when(repository.save(any(History.class))).thenReturn(historyTest);

            assertAll(
                    () -> assertNotNull(historyService.save(historyTest)),
                    () -> assertEquals(historyTest, historyService.save(historyTest))
            );
        }
    }

    @Nested
    @DisplayName("test Patch functionality")
    @Tag("Patch")
    class testPatch {
        @Test
        void checkPatch() {
            when(repository.save(any(History.class))).thenReturn(historyTest);
            when(repository.findById(2L)).thenReturn(Optional.ofNullable(historyTest));

            historyService.patch(2L, historyTest);
            assertAll(
                    () -> assertEquals(2L, historyTest.getId()),
                    () -> assertThrows(NotFoundException.class,
                            () -> historyService.patch(1L, historyTest)
                    ));
        }
    }

    @Nested
    @DisplayName("test deleteById functionality")
    @Tag("deleteById")
    class testDeleteById {

        @Test
        void deleteById() {
            when(repository.findById(1L)).thenReturn(Optional.of(historyTest));
            when(repository.findById(2L)).thenReturn(Optional.empty());

            History result = historyService.deleteById(1L);
            assertAll(
                    () -> assertNotNull(result),
                    () -> assertNotEquals("", result.toString()),
                    () -> assertEquals(historyTest, result),
                    () -> verify(repository,
                            times(1)).deleteById(any(Long.class)),
                    () -> assertThrows(NoSuchElementException.class,
                            () -> historyService.deleteById(2L))
            );
        }
    }
}