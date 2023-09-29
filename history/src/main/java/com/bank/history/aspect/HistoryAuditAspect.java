package com.bank.history.aspect;

import com.bank.history.entity.History;
import com.bank.history.entity.HistoryAudit;
import com.bank.history.service.HistoryAuditService;
import com.bank.history.service.HistoryService;
import com.bank.history.util.RandomUserCreated;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final HistoryService historyService;

    /**
     * Заполнение сущности аудит из контроллера save
     * параметр history - параметр из тела запроса
     */
    @Before(value = "execution(* com.bank.history.controller.HistoryControllerImpl.save(..)) && args( history)"
            , argNames = "history")
    public void save(History history) {
        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().toString())
                .operationType("create")
                .createdBy(RandomUserCreated.returnRandomUser())
                .createdAt(LocalDateTime.now())
                .entityJson(history.toString())
                .newEntityJson(null)
                .build();
        auditService.save(historyAudit);
    }

    /**
     * Аудирование метода update
     *
     * @param id             - id передаваемый в параметрах метода контроллера patch();
     * @param updatedHistory - json передаваемый в параметре метода контроллера, обновленная сущность
     */
    @Before(value = "execution(* com.bank.history.controller.HistoryControllerImpl.patch(..)) && args(id, updatedHistory)"
            , argNames = "id,updatedHistory")
    public void beforeUpdateHistory(Long id, History updatedHistory) {
        updatedHistory.setId(id);
        String idString = String.format("id=%d,", id);
        HistoryAudit historyAudits = auditService.findByEntityJsonContaining(idString);//получаем строку
        History history = historyService.getById(id);//находим старый джсон

        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(updatedHistory.getClass().getName())
                .operationType("update")
                .createdBy(historyAudits.getCreatedBy())
                .modifiedBy(RandomUserCreated.returnRandomUser())
                .createdAt(historyAudits.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .entityJson(history.toString())
                .newEntityJson(updatedHistory.toString())
                .build();
        auditService.save(historyAudit);

    }

    /**
     * Аудирование метода контроллера delete
     *
     * @param id - id из параметров метода контроллера deleteHistory()
     */
    @Before(value = "execution(* com.bank.history.controller.HistoryController.delete(..)) && args(id)"
            , argNames = "id")
    public void beforeDeleteHistory(Long id) {

        History history = historyService.getById(id);
        String idString = String.format("id=%d,", id);
        HistoryAudit historyAudits = auditService.findByEntityJsonContaining(idString);

        HistoryAudit historyAudit = HistoryAudit.builder()
                .entityType(history.getClass().getName())
                .operationType("delete")
                .createdBy(historyAudits.getCreatedBy())
                .modifiedBy(RandomUserCreated.returnRandomUser())
                .createdAt(historyAudits.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .entityJson(historyAudits.getEntityJson())
                .newEntityJson(null)
                .build();
        auditService.save(historyAudit);
    }
}


