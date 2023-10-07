package com.bank.history.service;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.HistoryAudit;
import com.bank.history.repository.HistoryAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryAuditServiceImpl implements HistoryAuditService {
    private final HistoryAuditRepository repository;

    @Override
    public List<HistoryAudit> showAll() {
        return repository.findAll();
    }

    @Override
    public HistoryAudit findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public HistoryAudit save(HistoryAudit historyAudit) {
        return repository.save(historyAudit);
    }

    @Override
    public HistoryAudit patch(Long id, HistoryAudit historyAudit) {
        historyAudit.setId(id);
        return repository.save(historyAudit);
    }

    @Override
    public HistoryAudit deleteById(Long id) {
        HistoryAudit audit = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return audit;
    }

    @Override
    public Optional<HistoryAudit> findByEntityJsonContaining(String value) {
        return repository.findByEntityJsonContaining(value);
    }

}
