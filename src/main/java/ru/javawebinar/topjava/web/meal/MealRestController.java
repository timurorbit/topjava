package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        return service.save(SecurityUtil.authUserId(), meal);
    }

    public void delete(int id){
        service.delete(SecurityUtil.authUserId(), id);
    }

    public Meal get(int id){
        return service.get(SecurityUtil.authUserId(), id);
    }

    public Meal update(int id, Meal meal) {
        return service.update(SecurityUtil.authUserId(), id, meal);
    }

    public Collection<MealTo> getAllFilteredByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate){
        if (startTime == null) startTime = LocalTime.MIN;
        if (endTime == null) endTime = LocalTime.MAX;
        if (startDate == null) startDate = LocalDate.MIN;
        if (endDate == null) endDate = LocalDate.MAX;
        return MealsUtil
                .getFilteredWithExcess(service.getAllFilteredByDate(SecurityUtil.authUserId(), startDate, endDate), MealsUtil.DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }
}