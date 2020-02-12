package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

public interface StorageMeal {

    void save(Meal meal);

    void update(Meal meal);

    void delete(String id);

    Meal get(String id);

    Map<String, Meal> getAll();
}
