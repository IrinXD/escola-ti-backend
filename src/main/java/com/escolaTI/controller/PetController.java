package com.escolaTI.controller;

import com.escolaTI.model.Pet;
import com.escolaTI.repository.PetRepository;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<Pet> obterPorId(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizar(@PathVariable String id, @RequestBody Pet petAtualizado) {
        return repository.findById(id)
                .map(pet -> {
                    pet.setNome(petAtualizado.getNome());
                    pet.setRaca(petAtualizado.getRaca());
                    pet.setFotoUrl(petAtualizado.getFotoUrl());
                    pet.setCriadorId(petAtualizado.getCriadorId());
                    pet.setCoTutoresIds(petAtualizado.getCoTutoresIds());
                    Pet salvo = repository.save(pet);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return repository.findById(id)
                .map(pet -> {
                    repository.delete(pet);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}