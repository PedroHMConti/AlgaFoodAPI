package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @Autowired
    private RestauranteRepository repo;

    @GetMapping
    public List<Restaurante> listar(){
        return repo.findAll();
    }


    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId){
        return cadastroRestaurante.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        return cadastroRestaurante.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formas_pagamento", "endereco", "dataCadastro");
        return cadastroRestaurante.salvar(restauranteAtual);
    }

    @DeleteMapping("/{restauranteId}")
    public void delete(@PathVariable Long restauranteId){
       cadastroRestaurante.excluir(restauranteId);
    }

    @GetMapping("/count-by-cozinha-id/{cozinhaId}")
    public int contar(@PathVariable  Long cozinhaId){
        return repo.countByCozinhaId(cozinhaId);
    }
}


