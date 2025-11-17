package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.Repository.UsuarioRepository;
import AlgaFoodAPI.Domain.service.CadastroUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{usuarioId}")
    public Usuario buscar(@PathVariable Long usuarioId){
        return cadastroUsuario.buscarOuFalhar(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar( @RequestBody @Valid Usuario usuario){
        return cadastroUsuario.salvar(usuario);
    }

    @PutMapping("/{usuarioId}")
    public Usuario atualizar(@PathVariable Long usuarioId, @RequestBody @Valid Usuario usuario){
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        BeanUtils.copyProperties(usuario,usuarioAtual,"id");
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
        return usuarioAtual;
    }

    @DeleteMapping("/{usuarioId}")
    public void excluir(@PathVariable Long usuarioId){
        cadastroUsuario.excluir(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.OK)
    public void alterarSenha(@PathVariable Long usuarioId,@RequestParam String senhaAtual, @RequestParam String senhaNova){
        cadastroUsuario.alterarSenha(usuarioId,senhaAtual,senhaNova);
    }
}
