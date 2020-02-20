package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepository.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
//    null if not found, when updated
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            return repository.computeIfAbsent(meal.getId(), id -> meal);
        }
        if (get(meal.getId(), userId) == null) {
            return null;
        } else {
            meal.setUserId(userId);
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    // false if not found
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Meal meal = repository.get(id);
        if (Objects.nonNull(meal) && (userId == meal.getUserId())) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    // null if not found
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        if (Objects.nonNull(meal)) {
            checkNotFoundWithId(meal.getUserId() == userId, id);
            return meal;
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return filteredByStreams(repository.values(), userId, meal -> true);
    }

    public Collection<Meal> getAllForDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAllForDateTime");
        return filteredByStreams(repository.values(), userId,
                meal -> (userId == meal.getUserId() && DateTimeUtil.isBetweenInclusive(meal.getDate(), startDate, endDate)));
    }

    private List<Meal> filteredByStreams(Collection<Meal> meals, int userId, Predicate<Meal> filter) {
        return meals.stream()
                .filter(meal -> userId == meal.getUserId())
                .filter(filter)
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }
}

