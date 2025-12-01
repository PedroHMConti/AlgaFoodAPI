package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.Repository.GrupoRepository;
import AlgaFoodAPI.Domain.service.CadastroGrupo;
import AlgaFoodAPI.api.assembler.GrupoInputDisassembler;
import AlgaFoodAPI.api.assembler.GrupoModelAssembler;
import AlgaFoodAPI.api.model.GrupoModel;
import AlgaFoodAPI.api.model.input.GrupoInput;
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

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar(){
        return grupoModelAssembler.toCollectionModel( grupoRepository.findAll());
    }

    @GetMapping("{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId){
        return grupoModelAssembler.toModel(cadastroGrupo.buscarOuFalhar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@Valid @RequestBody GrupoInput grupoInput){
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,@Valid @RequestBody GrupoInput grupoInput){
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        BeanUtils.copyProperties(grupo,grupoAtual,"id");
        return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long grupoId){
        cadastroGrupo.excluir(grupoId);
    }


}
