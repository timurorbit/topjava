package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query(Meal.DELETE)
    int delete(@Param("id") int id,@Param("userId") int userId);

    @Modifying
    @Query("SELECT FROM Meal m WHERE m.id =:id and m.user.id =: userId")
    Meal get(@Param("id") int id,@Param("userId") int userId);

    @Modifying
    @Query(Meal.ALL_SORTED)
    List<Meal> findAll(@Param("userId") int userId);

    @Query(Meal.GET_BETWEEN)
    List<Meal> findAllBetween(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
