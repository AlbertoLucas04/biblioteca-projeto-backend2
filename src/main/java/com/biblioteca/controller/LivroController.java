package com.biblioteca.controller;

import com.biblioteca.entity.Livro;
import com.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAll() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public Livro getById(@PathVariable Long id) {
        return livroService.findById(id).orElse(null);
    }

    @PostMapping
    public Livro create(@RequestBody Livro livro) {
        return livroService.save(livro);
    }

    @PutMapping("/{id}")
    public Livro update(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        return livroService.save(livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livroService.deleteById(id);
    }
}