package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;



public class MealsRepositoryImpl implements MealRepository {
    public final AtomicInteger ID = new AtomicInteger(6);
    private Map<Integer, Meal> mealsId = MealsUtil.fillBase().stream().collect(Collectors.toMap(Meal::getId, m -> m));

    public List<Meal> getAll() {
        return new ArrayList<>(mealsId.values());
    }

    public void delete(int id){
        mealsId.remove(id);
    }

    @Override
    public void edit(Meal meal) {
        mealsId.put(meal.getId(), meal);
    }

    public Meal getById(int id){
      return mealsId.get(id);
    }

    public int getId(){
        return ID.getAndIncrement();
    }
}
