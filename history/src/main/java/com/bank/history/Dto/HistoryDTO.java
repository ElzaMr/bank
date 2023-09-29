package com.bank.history.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Класс ДТО, нет поля id
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entity of History")
public class HistoryDTO {

    @Schema(description = "Entity Change ID HistoryAudit")
    private Long transferAuditId;

    @Schema(description = "Entity Change ID profileAuditId")
    private Long profileAuditId;

    @Schema(description = "Entity Change ID accountAuditId")
    private Long accountAuditId;

    @Schema(description = "Entity Change ID antiFraudAuditId")
    private Long antiFraudAuditId;

    @Schema(description = "Entity Change ID publicBankInfoAuditId")
    private Long publicBankInfoAuditId;

    @Schema(description = "Entity Change ID authorizationAuditId")
    private Long authorizationAuditId;


    public String toString() {
        return "{\n" +
                "\"transferAuditId\": " + transferAuditId + ",\n" +
                "\"profileAuditId\": " + profileAuditId + ",\n" +
                "\"accountAuditId\": " + accountAuditId + ",\n" +
                "\"antiFraudAuditId\": " + antiFraudAuditId + ",\n" +
                "\"publicBankInfoAuditId\": " + publicBankInfoAuditId + ",\n" +
                "\"authorizationAuditId\": " + authorizationAuditId + "\n" +
                '}';
    }
}
