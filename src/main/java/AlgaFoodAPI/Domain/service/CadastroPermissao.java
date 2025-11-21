package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.Repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissao {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao salvar(Permissao permissao){
        return permissaoRepository.save(permissao);
    }

    public void excluir(Long permissaoId) {
        try {
            Permissao permissao = buscarOuFalhar(permissaoId);
            permissaoRepository.delete(permissao);
        } catch (EmptyResultDataAccessException e) {
            throw new NegocioException(String.format("não existe permissão cadastrada com o código %d%n", permissaoId));
        }
    }

    public Permissao buscarOuFalhar(Long permissaoId){
        return permissaoRepository.findById(permissaoId).orElseThrow(() ->new NegocioException(String.format("não existe cadastro para a permissão com o código %d",permissaoId)));
    }
}
