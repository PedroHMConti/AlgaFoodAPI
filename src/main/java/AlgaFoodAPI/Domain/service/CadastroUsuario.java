package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Exception.UsuarioNotFoundException;
import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.Repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager manager;

    @Transactional
    public Usuario salvar(Usuario usuario){
        manager.detach(usuario);
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }


    public void excluir(Long usuarioId){
        try{
            buscarOuFalhar(usuarioId);
            usuarioRepository.deleteById(usuarioId);
        }catch(EmptyResultDataAccessException e){
            throw new UsuarioNotFoundException(usuarioId);
        }
    }
    public Usuario buscarOuFalhar(Long usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
    }

    @Transactional
    public void alterarSenha(Long usuarioId,String senhaAtual, String novaSenha){
        Usuario usuario = buscarOuFalhar(usuarioId);
        if(!usuario.getSenha().equals(senhaAtual)){
            throw new NegocioException("A senha informada não coincide com a senha do usuario.");
        }else{
            usuario.setSenha(novaSenha);
        }
    }
}
