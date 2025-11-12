package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.Repository.GrupoRepository;
import AlgaFoodAPI.Domain.service.CadastroGrupo;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/grupos")
@RestController
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupo cadastroGrupo;

    @GetMapping
    public List<Grupo> listar(){
        return grupoRepository.findAll();
    }

    @GetMapping("{grupoId}")
    public Grupo buscar(@PathVariable Long grupoId){
        return cadastroGrupo.buscarOuFalhar(grupoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grupo adicionar(@Valid @RequestBody Grupo grupo){
        return cadastroGrupo.salvar(grupo);
    }

    @PutMapping("/{grupoId}")
    public Grupo atualizar(@PathVariable Long grupoId,@Valid @RequestBody Grupo grupo){
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
        BeanUtils.copyProperties(grupo,grupoAtual,"id");
        return cadastroGrupo.salvar(grupoAtual);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long grupoId){
        cadastroGrupo.excluir(grupoId);
    }


}
