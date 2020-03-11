package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.repository.AbstractEntityRepository;

public abstract class EntityServiceTest {

    public AbstractEntityService service;

    public AbstractEntityRepository repository;

    protected EntityServiceTest(AbstractEntityService service, AbstractEntityRepository repository) {
        this.service = service;
        this.repository = repository;
    }
}
