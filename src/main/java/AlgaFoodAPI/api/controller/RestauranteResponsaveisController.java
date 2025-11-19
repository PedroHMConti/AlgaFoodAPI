package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import AlgaFoodAPI.Domain.service.CadastroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsaveisController {

    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @GetMapping
    public List<Usuario> listarResponsaveis(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return restaurante.getUsuarios();
    }

    @PutMapping("{usuarioId}")
    public void adicionarResponsaveis(@PathVariable Long restauranteId,@PathVariable Long usuarioId){
        cadastroRestaurante.associarResponsavel(restauranteId,usuarioId);
    }

    @DeleteMapping("{usuarioId}")
    public void desadicionarResponsaveis(@PathVariable Long restauranteId,@PathVariable Long usuarioId){
        cadastroRestaurante.desassociarResponsavel(restauranteId,usuarioId);
    }
}
