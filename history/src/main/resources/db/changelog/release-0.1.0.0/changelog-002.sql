--liquibase formatted sql

--changeset Elza:2
CREATE TABLE history.history_audit
(
    id              SERIAL PRIMARY KEY,
    entity_type     varchar(40),
    operation_type  varchar(255),
    created_by      varchar(255),
    modified_by     varchar(255),
    created_at      timestamp,
    modified_at     timestamp,
    new_entity_json text,
    entity_json     text
);