/*
package ru.hogwarts.school.repository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesByCategory extends JpaRepository <Exspense, Integer> {
    @Query(value = "SELECT category, SUM(amount) as amount FROM expenses GROUP BY category", nativeQuery = true)
    List<ExpensesByCategory> getExpensesByCategory();

    PageRequest var = PageRequest.of(0, 10);
}*/
