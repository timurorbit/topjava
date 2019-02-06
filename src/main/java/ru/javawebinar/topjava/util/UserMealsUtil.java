package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> test = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println(getFilteredWithExceededOnePass(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> SumCaloriesPerDays = new HashMap<>();
        for (UserMeal meal : mealList) {
            SumCaloriesPerDays.put(meal.getDateTime().toLocalDate(), SumCaloriesPerDays.getOrDefault(meal.getDateTime().toLocalDate(), 0) + meal.getCalories());
        }
        List<UserMealWithExceed> answer = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                answer.add(new UserMealWithExceed(meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        SumCaloriesPerDays.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return answer;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> SumCaloriesPerDays = mealList
                .stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));

        return mealList
                .stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), SumCaloriesPerDays.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }

    public static List<UserMealWithExceed> getFilteredWithExceededOnePass(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapSumCalories = new HashMap<>();
        Map<LocalDateTime, UserMeal> mapMeals = new HashMap<>();
        for (UserMeal meal : mealList){
            mapSumCalories.put(meal.getDateTime().toLocalDate(), mapSumCalories.getOrDefault(meal.getDateTime().toLocalDate(), 0) + meal.getCalories());
            mapMeals.put(meal.getDateTime(), meal);
        }
        List<UserMealWithExceed> answer = new ArrayList<>();
        for (Map.Entry<LocalDateTime, UserMeal> entry : mapMeals.entrySet()){
            UserMeal current = entry.getValue();
            if (TimeUtil.isBetween(current.getDateTime().toLocalTime(), startTime, endTime))
             answer.add(new UserMealWithExceed(current.getDateTime(), current.getDescription(), current.getCalories(), mapSumCalories.get(current.getDateTime().toLocalDate()) > caloriesPerDay));
        }
        return answer;
    }
}
