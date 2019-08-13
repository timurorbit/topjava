package ru.javawebinar.topjava.service;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.adjustEndDateTime;
import static ru.javawebinar.topjava.util.DateTimeUtil.adjustStartDateTime;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public interface MealService {

    Meal get(int id, int userId);

    void delete(int id, int userId);

    default List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return getBetweenDateTimes(adjustStartDateTime(startDate), adjustEndDateTime(endDate), userId);
    }

    public List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Meal> getAll(int userId);

    void update(Meal meal, int userId);

    Meal create(Meal meal, int userId);
}
