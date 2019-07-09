package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface MealService {

    Meal save(int userId, Meal meal);

    boolean delete(int userId, int id);

    Meal get(int userId, int id);

    Collection<Meal> getAll(int userId);

    Meal update(int userId, int id, Meal meal);
}