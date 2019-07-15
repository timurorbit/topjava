package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return ValidationUtil.checkNotFoundWithId(repository.save(userId, meal), userId);
    }

    @Override
    public void delete(int userId, int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Meal update(int userId, int id, Meal meal) {
        return ValidationUtil.checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    @Override
    public Collection<Meal> getAllFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAllFilteredByDate(userId, startDate, endDate);
    }
}