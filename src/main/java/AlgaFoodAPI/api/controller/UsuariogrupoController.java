package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.service.CadastroGrupo;
import AlgaFoodAPI.Domain.service.CadastroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuariogrupoController {

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @Autowired
    private CadastroGrupo cadastroGrupo;

    @GetMapping
    public List<Grupo> listarGrupos(@PathVariable Long usuarioId){
        return cadastroUsuario.buscarOuFalhar(usuarioId).getGrupos();
    }

    @PutMapping("{grupoId}")
    public void associarGrupo(@PathVariable Long usuarioId,@PathVariable Long grupoId){
        cadastroUsuario.associarGrupo(usuarioId,grupoId);
    }

    @DeleteMapping("{grupoId}")
    public void desassociarGrupo(@PathVariable Long usuarioId,@PathVariable Long grupoId){
        cadastroUsuario.desassociarGrupo(usuarioId,grupoId);
    }
}
