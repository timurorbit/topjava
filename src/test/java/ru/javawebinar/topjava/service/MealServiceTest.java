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
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

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
    public void getAll() throws Exception{
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_LIST);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWithFalseMealID() throws Exception{
        service.delete(9999,USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOtherUserMeal() throws Exception{
       service.delete(USER_ONE.getId(), ADMIN_ID);
    }

    @Test
    public void delete() throws Exception{
        service.delete(MealTestData.USER_ONE.getId(), USER_ID);
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = MEAL_LIST.subList(1,MEAL_LIST.size());
        assertMatch(actual, expected);
    }

    @Test
    public void create() throws Exception{
        Meal newMeal = new Meal(LocalDateTime.now(),"testNewMeal",1000);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
    }

    @Test
    public void get() throws Exception{
      Meal actual = service.get(USER_ONE.getId(), USER_ID);
      assertMatch(actual, USER_ONE);
    }

    @Test
    public void save() throws Exception{
        List<Meal> expected = new ArrayList<>(MEAL_LIST);
        expected.add(new Meal(START_SEQ + 14, LocalDateTime.of(2015, Month.MAY, 5, 5, 5),"test add", 666));    //14 cuz we have admin meals too
        service.create(new Meal(LocalDateTime.of(2015, Month.MAY, 5, 5, 5),"test add", 666), USER_ID);
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, expected);
    }

    @Test
    public void getBetweenTimes() throws Exception{
        List<Meal> expected = Arrays.asList(USER_ONE, USER_TWO, USER_THREE);
        List<Meal> actual = service.getBetweenDates(LocalDate.of(2015,1,1),LocalDate.of(2015,1,1),USER_ID);
        assertMatch(expected, actual);
    }

    @Test
    public void getBetweenDateTimes() throws Exception{
        List<Meal> expected = Arrays.asList(USER_FOUR);
        List<Meal> actual = service.getBetweenDateTimes(LocalDateTime.of(2015,1,2,4,0,1),
                LocalDateTime.of(2015,1,2,5,0,0),
                USER_ID);
        assertMatch(expected, actual);
    }


}
