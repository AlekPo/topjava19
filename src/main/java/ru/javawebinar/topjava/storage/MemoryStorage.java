package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MemoryStorage implements Storage {
    private static final Logger log = getLogger(MemoryStorage.class);

    private List<Meal> meals = new ArrayList<>();

    @Override
    public void save(Meal meal) {
        log.debug("Save " + meal);
        meals.add(meal);
    }

    @Override
    public void update(Meal meal) {
        log.debug("Update " + meal);
        meals.set(getSearchKey(meal.getId()), meal);
    }

    @Override
    public void delete(String id) {
        log.debug("Delete " + id);
        int searchKey = getSearchKey(id);
        meals.remove(searchKey);
    }

    @Override
    public void clear() {
        log.debug("Clear all");
        meals.clear();
    }

    @Override
    public int size() {
        log.debug("Size");
        return meals.size();
    }

    @Override
    public Meal get(String id) {
        log.debug("Get " + id);
        return meals.get(getSearchKey(id));
    }

    @Override
    public List<Meal> getAll() {
        log.debug("Get all");
        return meals;
    }

    private int getSearchKey(String id) {
        Meal mealTemp;
        for (int i = 0; i < meals.size(); i++) {
            mealTemp = meals.get(i);
            if (mealTemp.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}