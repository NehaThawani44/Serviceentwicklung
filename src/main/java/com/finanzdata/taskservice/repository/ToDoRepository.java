package com.finanzdata.taskservice.repository;



import com.finanzdata.taskservice.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByStatus(String status);

    @Query("SELECT t FROM ToDo t WHERE t.completionDate <= ?1")
    List<ToDo> findAllWithCompletionDateBefore(LocalDate date);


}

