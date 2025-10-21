package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroGrupo {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public void excluir(Long grupoId){
        try{
            grupoRepository.deleteById(grupoId);
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("não existe cadastro para grupo com código %d%n",grupoId));
        }
    }
}
