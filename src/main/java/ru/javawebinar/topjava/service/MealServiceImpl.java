package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.delete(userId, id);
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.get(userId, id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Meal update(int userId, int id, Meal meal) {
        return ValidationUtil.checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }
}