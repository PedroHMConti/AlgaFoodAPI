package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.service.CadastroGrupo;
import AlgaFoodAPI.Domain.service.CadastroPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupo cadastroGrupo;

    @Autowired
    private CadastroPermissao cadastroPermissao;

    @GetMapping
    public List<Permissao> listarPermissoes(@PathVariable Long grupoId){
        return cadastroGrupo.buscarOuFalhar(grupoId).getPermissoes();
    }

    @PutMapping("/{permissaoId}")
    public void AssociarPermissao(@PathVariable Long grupoId,@PathVariable Long permissaoId){
        cadastroGrupo.associarPermissao(grupoId,permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    public void DesassociarPermissao(@PathVariable Long grupoId,@PathVariable Long permissaoId){
        cadastroGrupo.desassociarPermissao(grupoId,permissaoId);
    }

}
