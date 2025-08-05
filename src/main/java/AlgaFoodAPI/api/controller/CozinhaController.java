package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.service.CadastroCozinha;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//GET /cozinhas HTTP/1.1

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {


    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinha cadastroCozinha;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();
    }


    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id){
        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if(cozinhaAtual != null){
            BeanUtils.copyProperties(cozinha,cozinhaAtual,"id");
            cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        try{
            cadastroCozinha.remover(cozinhaId);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
