package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsHolder;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class MealServlet extends HttpServlet {
    private MealsHolder holder = new MealsHolder();
    private List<Meal> mealsNoExcess;
    private List<MealTo> mealsWithExcess;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null){
            mealsNoExcess = new ArrayList<>(holder.getMeals().values());
            mealsWithExcess =  MealsUtil.getFilteredWithExcess(mealsNoExcess, LocalTime.MIN, LocalTime.MAX, 2000);    // 3 times repeat in code :)
            req.setAttribute("meals", mealsWithExcess);
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            holder.deleteMeal(mealId);
            mealsNoExcess = new ArrayList<>(holder.getMeals().values());
            mealsWithExcess =  MealsUtil.getFilteredWithExcess(mealsNoExcess, LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("meals", mealsWithExcess);
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("edit")){
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            req.setAttribute("meal", holder.getMealById(mealId));
            req.getRequestDispatcher("meal.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("add")){
            Meal meal = new Meal(holder.id.incrementAndGet(), LocalDateTime.now(), "description...", 0);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("meal.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("mealid")!= null){
            holder.deleteMeal(Integer.parseInt(req.getParameter("mealid")));
        }
        int id = Integer.parseInt(req.getParameter("mealid"));
        DateTimeFormatter ltdFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("mealDate"), ltdFormat);
        String description = req.getParameter("mealdescription");
        int calories = Integer.parseInt(req.getParameter("mealcalories"));
        Meal meal = new Meal(id, ldt, description, calories);

        holder.addMeal(meal);
        mealsNoExcess = new ArrayList<>(holder.getMeals().values());
        mealsWithExcess =  MealsUtil.getFilteredWithExcess(mealsNoExcess, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", mealsWithExcess);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
