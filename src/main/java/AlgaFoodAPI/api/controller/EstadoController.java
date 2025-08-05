package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import AlgaFoodAPI.Domain.service.CadastroEstado;
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
        return estadoRepository.listar();
    }


    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        Estado estado = estadoRepository.buscar(estadoId);
        if(estado != null){
            return ResponseEntity.ok(estado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
        Estado estado1 = cadastroEstado.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId,@RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.buscar(estadoId);
        if(estadoAtual != null){
            BeanUtils.copyProperties(estado,estadoAtual,"id");
            estadoAtual = estadoRepository.adicionar(estado);
            return ResponseEntity.ok(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> delete(@PathVariable Long estadoId){
        try{
            cadastroEstado.delete(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }


}
