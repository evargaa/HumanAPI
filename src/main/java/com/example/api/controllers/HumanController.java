package com.example.api.controllers;


import com.example.api.models.Human;
import com.example.api.repository.HumanRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/human")
@Validated
public class HumanController {

    @Autowired
    HumanRepository humanRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/")
    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    @PostMapping("/")
    public Human createHuman(@Valid @RequestBody Human human){
        return humanRepository.save(human);
    }

    @GetMapping("/{id}")
    public Human getHumanById(@PathVariable Long id) {
        return humanRepository.findById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Human> updateHumanById(@PathVariable Long id, @RequestBody Human updatedHuman) {
        Optional<Human> existingHuman = humanRepository.findById(id);
        if (existingHuman.isPresent()) {
            Human humanToUpdate = existingHuman.get();
            humanToUpdate.setAge(updatedHuman.getAge());
            humanToUpdate.setName(updatedHuman.getName());
            return ResponseEntity.ok(humanToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Human> deleteHuman(@PathVariable Long id){
        if(humanRepository.existsById(id)) {
            humanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@GetMapping("/search")
    public List<Human> findHuman(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "age", required = false) Integer age) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Human> query = criteriaBuilder.createQuery(Human.class);
        Root<Human> root = query.from(Human.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

        if (age != null) {
        predicates.add(criteriaBuilder.equal(root.get("age"), age));
    }

        if (!predicates.isEmpty()) {
        query.where(predicates.toArray(new Predicate[0]));
    }

        return entityManager.createQuery(query).getResultList();
    }
}

