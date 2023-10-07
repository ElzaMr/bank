//package com.bank.history.service;
//
//
//import com.bank.history.Dto.HistoryDTO;
//import com.bank.history.entity.History;
//import com.bank.history.mapper.HistoryMapper;
//import com.bank.history.repository.HistoryRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class HistoryServiceImplTest {
//
//    @Mock
//    private HistoryRepository repository;
//
//    @Mock
//    private HistoryMapper historyMapper;
//
//    //    @InjectMocks
//    private HistoryServiceImpl historyService;
//
//    private static History historyTest;
//    private static HistoryDTO historyDTOTest;
//
//    @BeforeAll
//    public static void setUp() {
//        historyTest = new History(1L, 2L, 3L, 4L, 5L, 6L, 7L);
//        historyDTOTest = new HistoryDTO(2L, 3L, 4L, 5L, 6L, 7L);
//    }
//
//    @BeforeEach
//    public void init() {
//        historyService = new HistoryServiceImpl(historyMapper, repository);
//    }
//
//    @Test
//    void showAll() {
//        List<History> histories = new ArrayList<>();
//        histories.add(historyTest);
//        when(repository.findAll()).thenReturn(histories);
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//        List<HistoryDTO> historyDTOList = historyService.showAll();
//
//        assertEquals(historyDTOList.size(), 1);
//        assertEquals(historyDTOList.get(0), historyDTOTest);
//    }
//
//    @Test
//    void showOne() {
//        when(repository.findById(any(Long.class))).thenReturn(Optional.of(historyTest));
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//        HistoryDTO result = historyService.findById(1L);
//
//        assertNotNull(result);
//        assertEquals(result, historyDTOTest);
//    }
//
//
//    @Test
//    void findById() {
//        when(repository.findById(any(Long.class))).thenReturn(Optional.of(historyTest));
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//        HistoryDTO result = historyService.findById(1L);
//
//        assertNotNull(result);
//        assertEquals(historyDTOTest, result);
//    }
//
//    @Test
//    public void save() {
//        when(repository.save(any(History.class))).thenReturn(historyTest);
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//       historyService.save(historyTest);
//
//        assertNotNull(result);
//        assertEquals(historyDTOTest, result);
//    }
//
//    @Test
//    void patch() {
//        when(repository.save(any(History.class))).thenReturn(historyTest);
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//        HistoryDTO result = historyService.patch(1L, historyTest);
//
//        assertNotNull(result);
//        assertEquals(historyDTOTest, result);
//    }
//
//    @Test
//    void deleteById() {
//        when(repository.findById(any(Long.class))).thenReturn(Optional.of(historyTest));
//        when(historyMapper.toDTO(historyTest)).thenReturn(historyDTOTest);
//
//        HistoryDTO result = historyService.deleteById(1L);
//
//        assertNotNull(result);
//        assertEquals(historyDTOTest, result);
//        verify(repository, times(1)).deleteById(any(Long.class));
//    }
//
//    @Test
//    void getById() {
//        when(repository.findById(any(Long.class))).thenReturn(Optional.of(historyTest));
//
//        History result = historyService.getById(1L);
//
//        assertNotNull(result);
//        assertEquals(historyTest, result);
//    }
//}