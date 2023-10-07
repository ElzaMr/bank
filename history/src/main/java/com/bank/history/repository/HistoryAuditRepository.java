package com.bank.history.repository;

import com.bank.history.entity.HistoryAudit;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Репозиторий AuditHistory
 */
public interface HistoryAuditRepository extends JpaRepository<HistoryAudit, Long> {
    /**
     * @param value SQL запрос к БД АудитаХистори ищет записи "create" в столбце "operation_type"
     *              и  "id=%d" где %d(номер id) приходит из запроса
     * @return записьHistoryAudit
     */
    @Query(value = "SELECT * FROM history.history_audit WHERE entity_json LIKE '%' || :value || '%'AND operation_type = 'create'", nativeQuery = true)
    Optional<HistoryAudit> findByEntityJsonContaining(@Param("value") String value);
}
