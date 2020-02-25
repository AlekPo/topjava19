package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {

    public static final Meal MEAL_1 = new Meal(101, LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "Пользователь ланч", 500);
    public static final Meal MEAL_2 = new Meal(102, LocalDateTime.of(2015, Month.JUNE, 1, 20, 0), "Пользователь ужин", 1400);
    public static final Meal MEAL_3 = new Meal(103, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal MEAL_4 = new Meal(104, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "Новая еда", 500);
    }
}
