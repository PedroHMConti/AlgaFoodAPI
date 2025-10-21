package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.Repository.GrupoRepository;
import AlgaFoodAPI.Domain.service.CadastroGrupo;
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
    private CadastroGrupo grupoCadastro;

    @GetMapping
    public List<Grupo> listar(){
        return grupoRepository.findAll();
    }

    @GetMapping("{grupoId}")
    public ResponseEntity<?> buscar(@PathVariable Long grupoId){
        try{
            Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para o grupo para o código %d",grupoId)));
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping()
    public ResponseEntity<?> adicionar(@RequestBody Grupo grupo){
        grupo = grupoRepository.save(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long grupoId,@RequestBody Grupo grupoAtualizado){
        try{
            Grupo grupoAntigo = grupoRepository.findById(grupoId).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("não existe cadastro para o grupo com o código %d%n",grupoId)));
            BeanUtils.copyProperties(grupoAtualizado,grupoAntigo,"id");
            grupoAtualizado = grupoRepository.save(grupoAtualizado);
            return ResponseEntity.ok(grupoAtualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @DeleteMapping("{grupoId}")
//    public ResponseEntity<?> deletar(@PathVariable Long grupoId){
//        try{
//            Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para o grupo para o código %d",grupoId)));
//        }
//    }


}
