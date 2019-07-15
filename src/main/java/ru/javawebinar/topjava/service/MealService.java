package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService {

    Meal save(int userId, Meal meal);

    void delete(int userId, int id);

    Meal get(int userId, int id);

    Collection<Meal> getAll(int userId);

    Meal update(int userId, int id, Meal meal);

    Collection<Meal> getAllFilteredByDate(int userId, LocalDate startDate, LocalDate endDate);
}