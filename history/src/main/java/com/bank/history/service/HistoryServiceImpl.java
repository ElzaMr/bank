package com.bank.history.service;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис модуля History реализует интерфейс HistoryService
 */
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;
    private final HistoryRepository repository;

    @Override
    public List<HistoryDTO> showAll() {
        return repository.findAll().stream().map(historyMapper::toDTO).toList();
    }

    @Override
    public HistoryDTO findById(Long id) {
        return historyMapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("History with id %d not found", id))));
    }

    @Transactional
    @Override
    public HistoryDTO save(History history) {
        return historyMapper.toDTO(repository.save(history));
    }

    @Transactional
    @Override
    public HistoryDTO patch(Long id, History history) {
        history.setId(id);
        return historyMapper.toDTO(repository.save(history));
    }

    @Transactional
    @Override
    public HistoryDTO deleteById(Long id) {
        HistoryDTO dto = historyMapper.toDTO(repository.findById(id).orElseThrow());//сначала нужно найти сущность, после удаления мы ее уже не найдем :)
        repository.deleteById(id);
        return dto;
    }

    public History getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("История с идентификатором %d не найдена", id)));
    }
}
