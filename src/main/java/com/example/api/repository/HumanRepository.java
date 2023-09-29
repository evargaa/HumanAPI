package com.example.api.repository;

import com.example.api.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumanRepository extends JpaRepository<Human, Long> {
    List<Human> findByNameAndAge(String name, int age);
    List<Human> findByName(String name);

    List<Human> findByAge(int age);
}
