package AlgaFoodAPI.Domain.service;


import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinha {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }



    public void excluir(Long cozinhaid){
        try {
            cozinhaRepository.deleteById(cozinhaid);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("não existe um cadastro de cozinha com o código %d",cozinhaid));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("cozinha de codigo %d não pode ser removida , pois está em uso", cozinhaid));
        }
    }
}
