package com.bank.history.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Min;

/**
 * Сущность History отвечает за историю и за все операции, которые отвечают за логику и за изменение сущностей данного микросервиса
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = "id")
@Builder
@Schema(description = "Entity History")
@Table(name = "history", schema = "history")
@EnableDiscoveryClient
public class History {
    @Schema(description = "ID number is generated automatically")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "ID number of record transferAuditId")
    @Min(1)
    private Long transferAuditId;

    @Schema(description = "ID number of record profileAuditId")
    @Min(value = 1, message = "The number must be greater than 0")
    private Long profileAuditId;

    @Schema(description = "ID number of record accountAuditId")
    @Min(value = 1, message = "The number must be greater than 0")
    private Long accountAuditId;

    @Schema(description = "ID number of record antiFraudAuditId")
    @Min(value = 1, message = "The number must be greater than 0")
    private Long antiFraudAuditId;

    @Schema(description = "ID number of record publicBankInfoAuditId")
    @Min(value = 1, message = "The number must be greater than 0")
    private Long publicBankInfoAuditId;

    @Schema(description = "ID number of record authorizationAuditId")
    @Min(value = 1, message = "The number must be greater than 0")
    private Long authorizationAuditId;

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", transferAuditId=" + transferAuditId +
                ", profileAuditId=" + profileAuditId +
                ", accountAuditId=" + accountAuditId +
                ", antiFraudAuditId=" + antiFraudAuditId +
                ", publicBankInfoAuditId=" + publicBankInfoAuditId +
                ", authorizationAuditId=" + authorizationAuditId +
                '}';
    }
}
