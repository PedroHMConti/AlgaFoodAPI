package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.CozinhaNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar( @RequestBody @Valid Restaurante restaurante) {
        try {
            return cadastroRestaurante.salvar(restaurante);

        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }



    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,@Valid @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formas_pagamento", "endereco", "dataCadastro");
        try {
            return cadastroRestaurante.salvar(restauranteAtual);
        }catch(CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
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


