package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repositoryMeals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> {
            meal.setUserId(1);
            save(meal.getUserId(), meal);
        });
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> userMeals;
            if (meal.isNew()) {
            userMeals = repositoryMeals.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
            meal.setUserId(userId);
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            return meal;
        }
        userMeals = repositoryMeals.get(userId);
        // treat case: update, but absent in storage
        return userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId,int id) {
        Map<Integer, Meal> userMeals = repositoryMeals.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Map<Integer, Meal> userMeals = repositoryMeals.get(userId);

        return userMeals != null ? userMeals.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repositoryMeals.get(userId)
                .values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

}

