package com.bank.history.controller;

import com.bank.history.entity.HistoryAudit;
import com.bank.history.service.HistorySuperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер класса АудитХистори
 */
@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor

public class HistoryAuditControllerImpl implements HistoryController<HistoryAudit, HistoryAudit> {

    private final HistorySuperService<HistoryAudit> service;

    @Tag(name = "GetAllAuditRecords", description = "Returns all audit records")
    @Override
    @GetMapping
    @Operation(description = "Return records from table History")
    public ResponseEntity<List<HistoryAudit>> showAll() {
        return ResponseEntity.ok(service.showAll());
    }

    @Tag(name = "GetAuditRecordsByID", description = "Returns audit records by id")
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<HistoryAudit> showOne(
            @Parameter(description = "HistoryAudit's id")
            @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Tag(name = "CreateAuditRecords", description = "Create audit records")
    @Override
    @PostMapping
    public ResponseEntity<HistoryAudit> save(
            @Parameter(description = "HistoryAudit's data")
            @RequestBody HistoryAudit historyAudit) {
        return ResponseEntity.ok(service.save(historyAudit));
    }

    @Tag(name = "UpdateAuditRecordsByID", description = "Update audit records by id")
    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<HistoryAudit> patch(
            @Parameter(description = "\n" +
                    "ID of the entity we want to change")
            @PathVariable Long id,
            @Parameter(description = "the data we want to place instead of the old ones")
            @RequestBody HistoryAudit updatedEntity) {
        return ResponseEntity.ok(service.patch(id, updatedEntity));
    }

    @Tag(name = "DeleteAuditRecords", description = "Delete audit records by id")
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<HistoryAudit> delete(@Parameter(description = "\n" +
            "ID of the entity you want to delete") @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
