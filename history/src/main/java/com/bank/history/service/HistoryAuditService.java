package com.bank.history.service;

import com.bank.history.entity.HistoryAudit;
import feign.Param;

import java.util.Optional;

/**
 * Интерфейс HistoryAuditService
 */
public interface HistoryAuditService extends HistorySuperService<HistoryAudit> {
    Optional<HistoryAudit> findByEntityJsonContaining(@Param("value") String value);
}
