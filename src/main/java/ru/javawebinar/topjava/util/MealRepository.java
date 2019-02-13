package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MealRepository {

    List<Meal> getAll();

    void delete(int id);

    void edit(Meal meal);

    Meal getById(int id);

    int getId();
}
