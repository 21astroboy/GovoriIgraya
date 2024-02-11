package com.example.govoriigraya.repo;

import com.example.govoriigraya.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.date >= :start AND c.date <= :end")
    List<Client> findAllByDateBetween(@Param("start") Timestamp start, @Param("end") Timestamp end);
}
