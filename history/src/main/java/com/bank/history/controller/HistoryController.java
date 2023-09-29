package com.bank.history.controller;

import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Интерфейс для контроллеров, содержит основные методы CRUD,
 * методы возвращают ResponseEntity параметризированный Дто
 *
 * @param <E> сущность
 * @param <D> Дто сущности
 */

public interface HistoryController<E, D> {
    ResponseEntity<List<D>> showAll();

    ResponseEntity<D> showOne(Long id);

    ResponseEntity<D> save(E entity);

    ResponseEntity<D> patch(Long id, E updatedEntity);

    ResponseEntity<D> delete(Long id);
}
