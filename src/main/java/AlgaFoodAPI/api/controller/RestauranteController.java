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
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Optional<Restaurante> restaurante = repo.findById(restauranteId);
        if(restaurante.isPresent()){
            return ResponseEntity.ok(restaurante.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try{
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = repo.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe restaurante cadastrado com código %d",restauranteId)));

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formas_pagamento","endereco","dataCadastro");

                restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> delete(@PathVariable Long restauranteId){
       try{
           Restaurante restaurante = repo.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para o restaurante com o código %d",restauranteId)));
           cadastroRestaurante.excluir(restauranteId);
           return ResponseEntity.noContent().build();

       } catch (EntidadeNaoEncontradaException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

    @GetMapping("/count-by-cozinha-id/{cozinhaId}")
    public int contar(@PathVariable  Long cozinhaId){
        return repo.countByCozinhaId(cozinhaId);
    }
}


