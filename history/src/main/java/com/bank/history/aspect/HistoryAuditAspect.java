package com.bank.history.aspect;

import com.bank.history.entity.History;
import com.bank.history.entity.HistoryAudit;
import com.bank.history.service.HistoryAuditService;
import com.bank.history.util.RandomUserCreated;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Класс с аспектами, в нем создаются и заполняются записи в таблицу аудит
 */
@Aspect
@Component
@RequiredArgsConstructor
public class HistoryAuditAspect {

    private final HistoryAuditService auditService;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * Заполнение сущности аудит из метода save сервиса
     * history - возвращаемое значение из метода сервиса
     */
    @AfterReturning(value = "execution(* com.bank.history.service.HistoryServiceImpl.save(..))"
            , returning = "history")
    public void afterSave(History history) {

        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().toString())
                .operationType("create")
                .createdBy(RandomUserCreated.returnRandomUser())
                .createdAt(localDateTime)
                .entityJson(history.toString())
                .newEntityJson(null)
                .build();
        auditService.save(historyAudit);
    }

    /**
     * Аудирование метода update
     * history - возвращаемое значение из метода сервиса
     */
    @AfterReturning(value = "execution(* com.bank.history.service.HistoryServiceImpl.patch(..))"
            , returning = "history", argNames = "history")
    public void afterUpdateHistory(History history) {

        HistoryAudit historyAudits = auditService
                .findByEntityJsonContaining(String.format("id=%d", history.getId()))
                .orElseThrow();

        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().getName())
                .operationType("update")
                .createdBy(historyAudits.getCreatedBy())
                .modifiedBy(RandomUserCreated.returnRandomUser())
                .createdAt(historyAudits.getCreatedAt())
                .modifiedAt(localDateTime)
                .entityJson(historyAudits.getEntityJson())//старый json
                .newEntityJson(history.toString())
                .build();
        auditService.save(historyAudit);

    }

    /**
     * Аудирование метода контроллера delete
     * history - возвращаемое значение из метода сервиса
     */
    @AfterReturning(value = "execution(* com.bank.history.service.HistoryServiceImpl.deleteById(..))"
            , returning = "history", argNames = "history")
    public void afterDeleteHistory(History history) {

        HistoryAudit historyAudits = auditService
                .findByEntityJsonContaining(String.format("id=%d", history.getId()))
                .orElseThrow();

        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().getName())
                .operationType("delete")
                .createdBy(historyAudits.getCreatedBy())
                .modifiedBy(RandomUserCreated.returnRandomUser())
                .createdAt(historyAudits.getCreatedAt())
                .modifiedAt(localDateTime)
                .entityJson(historyAudits.getEntityJson())
                .newEntityJson(null)
                .build();
        auditService.save(historyAudit);
    }
}


