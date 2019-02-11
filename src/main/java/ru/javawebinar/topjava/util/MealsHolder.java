package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;



public class MealsHolder {
    public final AtomicInteger id = new AtomicInteger(5);
    private List<Meal> mealsNotExcess = Arrays.asList(
            new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );
    private Map<Integer, Meal> mealsId = mealsNotExcess.stream().collect(Collectors.toMap(Meal::getId, m -> m));

    public Map<Integer, Meal> getMeals() {
        return mealsId;
    }

    public void addMeal(Meal meal){
        mealsId.put(meal.getId(), meal);
    }

    public void deleteMeal(int id){
        mealsId.remove(id);
    }

    public Meal getMealById(int id){
      return mealsId.get(id);
    }

}
