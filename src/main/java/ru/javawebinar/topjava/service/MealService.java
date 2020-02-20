package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Logger log = getLogger(MealService.class);

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        return repository.save(meal, userId);
    }

    public void update(Meal meal, int userId) {
        log.info("update {} with id={}", meal, meal.getId());
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.getAll(userId);
    }

    public Collection<Meal> getAllForDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAllForDateTime");
        return repository.getAllForDate(userId, startDate, endDate);
    }
}