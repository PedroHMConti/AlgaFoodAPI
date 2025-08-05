package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.Repository.PermissaoRepository;
import AlgaFoodAPI.Infrastructure.Repository.PermissaoRepositoryImplamentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
@ResponseBody
public class PermissaoController {

    @Autowired
    private PermissaoRepositoryImplamentation repo;

    @GetMapping
    public List<Permissao> listar(){
        return repo.listar();
    }

    @GetMapping("/{permissaoId}")
    public ResponseEntity<Permissao> buscar(@PathVariable("permissaoId") Long id){
        Permissao permissao  = repo.buscar(id);
        if(permissao != null){
            return ResponseEntity.ok(permissao);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
