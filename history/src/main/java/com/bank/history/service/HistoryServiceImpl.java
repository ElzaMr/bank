package com.bank.history.service;

import com.bank.history.entity.History;
import com.bank.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

/**
 * Сервис модуля History реализует интерфейс HistoryService
 */
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository repository;

    @Override
    public List<History> showAll() {
        return repository.findAll().stream().toList();
    }

    @Override
    public History findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("History with id %d not found", id)));
    }

    @Transactional
    @Override
    public History save(History history) {
        return repository.save(history);
    }

    @Transactional
    @Override
    public History patch(Long id, History history) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("History with id " + id + " not found"));
        history.setId(id);
        return repository.save(history);
    }

    @Transactional
    @Override
    public History deleteById(Long id) {
        History history = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return history;
    }
}
