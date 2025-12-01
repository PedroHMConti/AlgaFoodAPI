package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import AlgaFoodAPI.Domain.service.CadastroEstado;
import AlgaFoodAPI.api.assembler.EstadoInputDesassembler;
import AlgaFoodAPI.api.assembler.EstadoModelAssembler;
import AlgaFoodAPI.api.model.EstadoModel;
import AlgaFoodAPI.api.model.input.EstadoInput;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estados")
@ResponseBody
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstado cadastroEstado;

    @Autowired
    private EstadoInputDesassembler estadoInputDesassembler;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public List<EstadoModel> listar(){

        return estadoModelAssembler.collectionToModel( estadoRepository.findAll());
    }


    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId){

        return estadoModelAssembler.toModel( cadastroEstado.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
        Estado estado = estadoInputDesassembler.toDomainObject(estadoInput);

        return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));
    }


    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,@Valid @RequestBody EstadoInput estadoInput){
        Estado estado = estadoInputDesassembler.toDomainObject(estadoInput);
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado,estadoAtual,"id");
        return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));

    }

    @DeleteMapping("/{estadoId}")
    public void delete(@PathVariable Long estadoId){
        cadastroEstado.excluir(estadoId);
    }

}
