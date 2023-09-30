package com.bank.history.controller;

import com.bank.history.Dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.service.HistorySuperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Класс контроллеров
 * Логгирование вынесено в клас com.bank.history.aspect.HistoryAspectLogging
 * HandlerException вынесен в com.bank.history.handler.GlobalExceptionHandler
 */

@RestController
@RequiredArgsConstructor
public class HistoryControllerImpl implements HistoryController<History, HistoryDTO> {
    private final HistorySuperService<History, HistoryDTO> serviceHistory;

    @Operation(summary = "Get list of history")
    @Override
    @GetMapping()
    public ResponseEntity<List<HistoryDTO>> showAll() {
        return ResponseEntity.ok(serviceHistory.showAll());
    }

    @Operation(summary = "Get history by id")
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<HistoryDTO> showOne(@Parameter(description = "id of the history you want to find") @PathVariable Long id) {
        return ResponseEntity.ok(serviceHistory.findById(id));
    }

    @Operation(summary = "Create a new history")
    @Override
    @PostMapping()
    public ResponseEntity<HistoryDTO> save(@Parameter(description = "ID of the story you want to change")@Validated @RequestBody History entity) {
        return ResponseEntity.ok(serviceHistory.save(entity));
    }

    @Operation(summary = "Update history by id")
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<HistoryDTO> patch(@Parameter(description = "id of the history") @PathVariable Long id, @Parameter(description = "new history data")@Valid @RequestBody History updatedHistory) {
        return ResponseEntity.ok(serviceHistory.patch(id, updatedHistory));
    }

    @Operation(summary = "Delete history by id")
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<HistoryDTO> delete(@Parameter(description = "ID of the story you want to delete") @PathVariable Long id) {
        return ResponseEntity.ok(serviceHistory.deleteById(id));
    }
}
