package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;

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
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER.getId());
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertThat(created).isEqualTo(newMeal);
        assertThat(service.get(newId, USER.getId())).isEqualTo(newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(MEAL_1.getId(), USER.getId());
        service.get(MEAL_1.getId(), USER.getId());
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() {
        service.delete(1, 1);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1.getId(), USER.getId());
        assertThat(meal).isEqualTo(MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, 1);
    }

    @Test
    public void getBetweenHalfOpen() {
        List<Meal> meals = service.getBetweenHalfOpen(null, null, USER.getId());
        assertThat(meals).isEqualTo(Arrays.asList(MEAL_2, MEAL_1));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER.getId());
        assertThat(all).isEqualTo(Arrays.asList(MEAL_2, MEAL_1));
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_1);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("UpdatedMeal");
        updated.setCalories(111);
        service.update(updated, USER.getId());
        assertThat(service.get(updated.getId(), USER.getId())).isEqualTo(updated);
    }
}