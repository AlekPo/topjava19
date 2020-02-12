package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MemoryStorageMeal implements StorageMeal {
    private static final Logger log = getLogger(MemoryStorageMeal.class);

    private Map<String, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public void save(Meal meal) {
        log.debug("Save " + meal);
        meals.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        log.debug("Update " + meal);
        meals.replace(meal.getId(), meal);
    }

    @Override
    public void delete(String id) {
        log.debug("Delete " + id);
        meals.remove(id);
    }

    @Override
    public Meal get(String id) {
        log.debug("Get " + id);
        return meals.get(id);
    }

    @Override
    public Map<String, Meal> getAll() {
        log.debug("Get all");
        return meals;
    }
}