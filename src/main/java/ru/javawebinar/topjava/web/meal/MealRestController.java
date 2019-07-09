package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        return service.save(SecurityUtil.authUserId(), meal);
    }

    public boolean delete(int id){
        return service.delete(SecurityUtil.authUserId(), id);
    }

    public Meal get(int id){
        return service.get(SecurityUtil.authUserId(), id);
    }

    public Collection<Meal> getAll(){
        return service.getAll(SecurityUtil.authUserId());
    }

    public Meal update(int id, Meal meal) {
        return service.update(SecurityUtil.authUserId(), id, meal);
    }
}