package com.escolaTI.controller;

import com.escolaTI.model.Pet;
import com.escolaTI.repository.PetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Pet criar(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @GetMapping
    public List<Pet> listar() {
        return repository.findAll();
    }
}