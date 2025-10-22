package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.EstadoNaoEncontradoException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Cozinha;
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
    public Cidade buscar(@PathVariable Long cidadeId){
        return cadastroCidade.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cadastroCidade.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    public void delete(@PathVariable Long cidadeId){
        cadastroCidade.excluir(cidadeId);
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
