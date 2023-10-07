package com.bank.history.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Класс ДТО, нет поля id
 */
@Builder
@Schema(description = "History's DTO")
public record HistoryDTO(
        @Schema(description = "Entity Change ID HistoryAudit")
        Long transferAuditId,

        @Schema(description = "Entity Change ID profileAuditId")
        Long profileAuditId,

        @Schema(description = "Entity Change ID accountAuditId")
        Long accountAuditId,

        @Schema(description = "Entity Change ID antiFraudAuditId")
        Long antiFraudAuditId,

        @Schema(description = "Entity Change ID publicBankInfoAuditId")
        Long publicBankInfoAuditId,

        @Schema(description = "Entity Change ID authorizationAuditId")
        Long authorizationAuditId) {

    @Override
    public Long transferAuditId() {
        return transferAuditId;
    }

    @Override
    public Long profileAuditId() {
        return profileAuditId;
    }

    @Override
    public Long accountAuditId() {
        return accountAuditId;
    }

    @Override
    public Long antiFraudAuditId() {
        return antiFraudAuditId;
    }

    @Override
    public Long publicBankInfoAuditId() {
        return publicBankInfoAuditId;
    }

    @Override
    public Long authorizationAuditId() {
        return authorizationAuditId;
    }
}
