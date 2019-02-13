package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealRepository;
import ru.javawebinar.topjava.util.MealsRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MealServlet extends HttpServlet {
    private MealRepository holder = new MealsRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mealId;
        String action = req.getParameter("action");
        if (action == null){
             action = "getAll";
        }
        switch (action){
            case "edit":
                mealId = Integer.parseInt(req.getParameter("mealId"));
                req.setAttribute("meal", holder.getById(mealId));
                req.getRequestDispatcher("meal.jsp").forward(req, resp);
                break;
            case "add":
                Meal meal = new Meal(-1, LocalDateTime.now(), "description...", 0);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("meal.jsp").forward(req, resp);
                break;
            case "delete":
                mealId = Integer.parseInt(req.getParameter("mealId"));
                holder.delete(mealId);
            case "getAll":
                List<Meal> mealsNoExcess = holder.getAll();
                List<MealTo> mealsWithExcess = MealsUtil.getFilteredWithExcess(mealsNoExcess, LocalTime.MIN, LocalTime.MAX, 2000);
                req.setAttribute("meals", mealsWithExcess);
                req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("mealid"));
        if (id == -1){
            id = holder.getId();
        }
        DateTimeFormatter ltdFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("mealDate"), ltdFormat);
        String description = req.getParameter("mealdescription");
        int calories = Integer.parseInt(req.getParameter("mealcalories"));
        Meal meal = new Meal(id, ldt, description, calories);

        holder.edit(meal);
        resp.sendRedirect("meals");
    }
}
