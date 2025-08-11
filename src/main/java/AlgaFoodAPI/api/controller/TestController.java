package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> consultaPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findByNome(nome);
    }
}
