package com.bank.history.service;

import com.bank.history.entity.HistoryAudit;
import feign.Param;

/**
 * Интерфейс HistoryAuditService
 */
public interface HistoryAuditService extends HistorySuperService<HistoryAudit, HistoryAudit> {
    HistoryAudit findByEntityJsonContaining(@Param("value") String value);
}
