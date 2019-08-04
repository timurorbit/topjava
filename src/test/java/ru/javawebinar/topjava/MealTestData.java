package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal USER_ONE = new Meal(START_SEQ + 2, LocalDateTime.of(2015,1,1,1,0,1)
            , "one", 201);

    public static final Meal USER_TWO = new Meal(START_SEQ + 3, LocalDateTime.of(2015,1,1,2,0,1)
            , "two", 202);

    public static final Meal USER_THREE = new Meal(START_SEQ + 4, LocalDateTime.of(2015,1,1,3,0,1)
            , "three", 203);

    public static final Meal USER_FOUR = new Meal(START_SEQ + 5, LocalDateTime.of(2015,1,2,4,0,1)
            , "for", 2004);

    public static final Meal USER_FIVE = new Meal(START_SEQ + 6, LocalDateTime.of(2015,1,2,5,0,1)
            , "five", 205);

    public static final Meal USER_SIX = new Meal(START_SEQ + 7, LocalDateTime.of(2015,1,2,6,0,1)
            , "six", 206);

    public static final List<Meal> MEAL_LIST = Arrays.asList(USER_ONE, USER_TWO, USER_THREE, USER_FOUR, USER_FIVE, USER_SIX);

    public static void assertMatch(Meal actual, Meal excepted){
     assertThat(actual).isEqualTo(excepted);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

            /*
              ('2015-01-01 00:00:01', 'one', 201, 100000),
  ('2015-01-01 00:00:01', 'two', 202, 100000),
  ('2015-01-01 00:00:01', 'three', 203, 100000),
  ('2015-01-02 00:00:01', 'four', 2004, 100000),
  ('2015-01-02 00:00:01', 'five', 205, 100000),
  ('2015-01-02 00:00:01', 'six', 206, 100000),

  ('2015-01-02 00:00:01', 'admin1', 200, 100001),
  ('2015-01-02 00:00:01', 'admin2', 350, 100001),
  ('2015-01-02 00:00:01', 'admin3', 240, 100001),
  ('2015-01-03 00:00:01', 'admin4', 560, 100001),
  ('2015-01-03 00:00:01', 'admin5', 840, 100001),
  ('2015-01-03 00:00:01', 'admin6', 720, 100001);
             */
}
