package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.Repository.UsuarioRepository;
import AlgaFoodAPI.Domain.service.CadastroUsuario;
import AlgaFoodAPI.api.assembler.UsuarioInputDisassembler;
import AlgaFoodAPI.api.assembler.UsuarioModelAssembler;
import AlgaFoodAPI.api.model.UsuarioModel;
import AlgaFoodAPI.api.model.input.SenhaInput;
import AlgaFoodAPI.api.model.input.UsuarioComSenhaInput;
import AlgaFoodAPI.api.model.input.UsuarioInput;
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

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public List<UsuarioModel> listar(){
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId){
        return usuarioModelAssembler.toModel(cadastroUsuario.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar( @RequestBody @Valid UsuarioComSenhaInput usuarioInput){
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput){
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @DeleteMapping("/{usuarioId}")
    public void excluir(@PathVariable Long usuarioId){
        cadastroUsuario.excluir(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
