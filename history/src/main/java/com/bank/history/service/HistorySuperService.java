package com.bank.history.service;

import java.util.List;

public interface HistorySuperService<Entity, Dto> {
    public List<Dto> showAll();

    public Dto findById(Long id);

    public Dto save(Entity entity);

    Dto patch(Long id, Entity entity);

    public Dto deleteById(Long id);

}
