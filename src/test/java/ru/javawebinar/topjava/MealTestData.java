package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;
    public static final int MEAL_3_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_5_ID = START_SEQ + 6;
    public static final int MEAL_6_ID = START_SEQ + 7;
    public static final int MEAL_7_ID = START_SEQ + 8;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2015, Month.JUNE, 1, 13, 0), "Пользователь ланч", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, LocalDateTime.of(2015, Month.JUNE, 1, 20, 0), "Пользователь ужин", 1400);
    public static final Meal MEAL_3 = new Meal(MEAL_3_ID, LocalDateTime.of(2015, Month.JUNE, 1, 20, 0), "Админ еще ужин", 1400);
    public static final Meal MEAL_4 = new Meal(MEAL_4_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal MEAL_5 = new Meal(MEAL_5_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);
    public static final Meal MEAL_6 = new Meal(MEAL_6_ID, LocalDateTime.of(2015, Month.JUNE, 2, 13, 10), "Пользователь ланч", 300);
    public static final Meal MEAL_7 = new Meal(MEAL_7_ID, LocalDateTime.of(2015, Month.JUNE, 3, 20, 10), "Пользователь ужин", 2200);


    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "Новая еда", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
