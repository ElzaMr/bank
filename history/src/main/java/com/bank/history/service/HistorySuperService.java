package com.bank.history.service;

import liquibase.pro.packaged.D;

import java.util.List;

public interface HistorySuperService<Entity> {
    public List<Entity> showAll();

    public Entity findById(Long id);

    public Entity save(Entity entity);

    Entity patch(Long id, Entity entity);

    public Entity deleteById(Long id);

}
