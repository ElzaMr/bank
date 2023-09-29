package com.bank.history.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность Аудита
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(schema = "history", name = "history_audit")
public class HistoryAudit {

    @Schema(description = "ID number is generated automatically")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "class must be specified")
    private String entityType;

    @Schema(description = "indicate the type of operation")
    private String operationType;

    @Schema(description = "the person who created record")
    private String createdBy;

    @Schema(description = "the person who modified record")
    private String modifiedBy;

    @Schema(description = "time when the record was created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "time when the record was modified")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    @Schema(description = "new Json of the entity")
    private String newEntityJson;

    @Schema(description = "old Json of the entity")
    private String entityJson;
}
