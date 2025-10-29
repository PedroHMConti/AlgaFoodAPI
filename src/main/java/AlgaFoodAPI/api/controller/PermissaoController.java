package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.Repository.PermissaoRepository;
import AlgaFoodAPI.Domain.service.CadastroPermissao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroPermissao cadastroPermissao;

    @GetMapping
    public List<Permissao> listar(){
        return permissaoRepository.findAll();
    }

    @GetMapping("/{permissaoId}")
    public ResponseEntity<?> buscar(@PathVariable Long permissaoId){
        try{
            Permissao permissao  = permissaoRepository.findById(permissaoId).orElseThrow(() ->new NegocioException(String.format("não existe cadastro para a permissão com o código %d",permissaoId)));
            return ResponseEntity.ok(permissao);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Permissao> adicionar(@RequestBody Permissao permissao){
        return ResponseEntity.ok(cadastroPermissao.salvar(permissao));
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long permissaoId,@RequestBody Permissao permissao){
        try{
            Permissao permissaoAtual  = permissaoRepository.findById(permissaoId).orElseThrow(() ->new NegocioException(String.format("não existe cadastro para a permissão com o código %d",permissaoId)));
            BeanUtils.copyProperties(permissao,permissaoAtual,"id");
            permissaoAtual =permissaoRepository.save(permissaoAtual);
            return ResponseEntity.ok(permissaoAtual);
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<?> delete(@PathVariable Long permissaoId){
        try{
            Permissao permissao  = permissaoRepository.findById(permissaoId).orElseThrow(() ->new NegocioException(String.format("não existe cadastro para a permissão com o código %d",permissaoId)));
            cadastroPermissao.excluir(permissaoId);
            return ResponseEntity.noContent().build();
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
