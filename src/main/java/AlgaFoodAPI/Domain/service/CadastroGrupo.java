package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.GrupoNotFoundException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Grupo;
import AlgaFoodAPI.Domain.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            buscarOuFalhar(grupoId);
            grupoRepository.deleteById(grupoId);
        }catch(EmptyResultDataAccessException e){
            throw new GrupoNotFoundException(String.format("não existe cadastro para grupo com código %d%n",grupoId));
        }catch (DataIntegrityViolationException e ){
            throw new EntidadeEmUsoException(String.format("O grupo com o código %d não pode ser excluido pois está em uso",grupoId));
        }
    }
    public Grupo buscarOuFalhar(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNotFoundException(grupoId));
    }
}
