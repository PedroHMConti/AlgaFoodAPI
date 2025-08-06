package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.CidadeRepository;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import AlgaFoodAPI.Domain.service.CadastroCidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidade cadastroCidade;

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Cidade> listar(){
        return repository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<?> buscar(@PathVariable Long cidadeId){
        Cidade cidade =  repository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para a cidade com o código %d",cidadeId)));
        if(cidade == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
        try{
            cadastroCidade.salvar(cidade);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId,@RequestBody Cidade cidade){
        try{
            Cidade cidadeAtual = repository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para a cidade com o código %d",cidadeId)));
            if(cidadeAtual != null){
                BeanUtils.copyProperties(cidade,cidadeAtual,"id");
                cidadeAtual = repository.save(cidadeAtual);
                return ResponseEntity.ok(cidadeAtual);
            }
            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> delete(@PathVariable Long cidadeId){
        try{
            Cidade cidade  = repository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para a cidade com o código %d",cidadeId)));
            cadastroCidade.delete(cidadeId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PatchMapping("/{cidadeId}")
//    public ResponseEntity<?> atualizarPartical(@PathVariable Long cidadeId, @RequestBody Map<String, Object> campos){
//        Cidade cidade = repository.buscar(cidadeId);
//        if(cidade == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        merge(campos,cidade);
//
//
//        return atualizar(cidadeId,cidade);
//    }
//
//    public void merge(Map<String,Object> camposOrigem,Cidade cidadeDestino){
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Cidade cidadeOrigem = objectMapper.convertValue(camposOrigem,Cidade.class);
//        camposOrigem.forEach((nomePropriedade,valorPropriedade) ->{
//            Field field = ReflectionUtils.findField(Cidade.class,nomePropriedade);
//            field.setAccessible(true);
//
//            Object novoValor = ReflectionUtils.getField(field,cidadeOrigem);
//
//            System.out.println(nomePropriedade + " = "+ valorPropriedade);
//            ReflectionUtils.setField(field,cidadeDestino,novoValor);
//        });
//    }
}
