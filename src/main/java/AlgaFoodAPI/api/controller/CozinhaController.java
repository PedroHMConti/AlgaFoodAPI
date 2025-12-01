package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.service.CadastroCozinha;
import AlgaFoodAPI.api.assembler.CozinhaInputDesassembler;
import AlgaFoodAPI.api.assembler.CozinhaModelAssembler;
import AlgaFoodAPI.api.model.CozinhaModel;
import AlgaFoodAPI.api.model.input.CozinhaInput;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//GET /cozinhas HTTP/1.1

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {


    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinha cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDesassembler cozinhaInputDesassembler;
    @GetMapping
    public List<CozinhaModel> listar() {

        return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
    }


    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long id) {

        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDesassembler.toDomainObject(cozinhaInput);
        cozinha = cadastroCozinha.salvar(cozinha);
        return cozinhaModelAssembler.toModel(cozinha);
    }


    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        BeanUtils.copyProperties(cozinhaInputDesassembler.toDomainObject(cozinhaInput), cozinhaAtual, "id");
        return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaInputDesassembler.toDomainObject(cozinhaInput)));
    }

    @DeleteMapping("/{cozinhaId}")
    public void delete(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }

}
