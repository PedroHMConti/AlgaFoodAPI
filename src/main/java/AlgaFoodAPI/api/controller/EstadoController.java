package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import AlgaFoodAPI.Domain.service.CadastroEstado;
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

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }


    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId){
        return cadastroEstado.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody @Valid Estado estado){
        return cadastroEstado.salvar(estado);
    }


    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId,@Valid @RequestBody Estado estado){

        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado,estadoAtual,"id");
        return cadastroEstado.salvar(estadoAtual);

    }

    @DeleteMapping("/{estadoId}")
    public void delete(@PathVariable Long estadoId){
        cadastroEstado.excluir(estadoId);
    }

}
