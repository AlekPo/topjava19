package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = MealTestData.getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        service.delete(MEAL_1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAndGet() {
        service.delete(MEAL_1_ID, USER_ID);
        service.get(MEAL_1_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() {
        service.delete(1, 1);
    }

    @Test(expected = NotFoundException.class)
    public void deletedAlienMeal() {
        service.delete(MEAL_1_ID, ADMIN_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1_ID, USER_ID);
        assertMatch(meal, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, 1);
    }

    @Test(expected = NotFoundException.class)
    public void getAlienMeal() {
        service.get(MEAL_1_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> meals = service.getBetweenHalfOpen(LocalDate.of(2015, Month.JUNE, 1), LocalDate.of(2015, Month.JUNE, 2), USER_ID);
        assertMatch(meals, MEAL_6, MEAL_2, MEAL_1);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_7, MEAL_6, MEAL_2, MEAL_1);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_1);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("UpdatedMeal");
        updated.setCalories(111);
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        Meal updated = new Meal(MEAL_1);
        updated.setId(1);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("UpdatedMeal");
        updated.setCalories(111);
        service.update(updated, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlienMeal() {
        Meal updated = new Meal(MEAL_1);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("UpdatedMeal");
        updated.setCalories(111);
        service.update(updated, ADMIN_ID);
    }
}