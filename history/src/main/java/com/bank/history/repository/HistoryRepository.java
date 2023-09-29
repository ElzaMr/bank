package com.bank.history.repository;

import com.bank.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий History
 */
public interface HistoryRepository extends JpaRepository<History, Long> {
}
