package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Usuario;
import AlgaFoodAPI.Domain.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class CadastroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long usuarioId){
        try{
            usuarioRepository.deleteById(usuarioId);
        }catch(EmptyResultDataAccessException e){
            throw new NegocioException(String.format("não existe cadastro para o usuário com o código %d%n",usuarioId));
        }
    }
}
