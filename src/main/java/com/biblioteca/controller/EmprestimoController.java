package com.biblioteca.controller;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<Emprestimo> getAll() {
        return emprestimoService.findAll();
    }

    @GetMapping("/{id}")
    public Emprestimo getById(@PathVariable Long id) {
        return emprestimoService.findById(id).orElse(null);
    }

    @PostMapping
    public Emprestimo create(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.save(emprestimo);
    }

    @PutMapping("/{id}")
    public Emprestimo update(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setId(id);
        return emprestimoService.save(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        emprestimoService.deleteById(id);
    }
}