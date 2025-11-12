package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Exception.UsuarioNotFoundException;
import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
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
}
