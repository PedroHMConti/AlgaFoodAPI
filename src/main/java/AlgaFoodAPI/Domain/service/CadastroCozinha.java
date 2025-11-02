package AlgaFoodAPI.Domain.service;


//import AlgaFoodAPI.Domain.Exception.CozinhaNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.CozinhaNaoEncontradaException;
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
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastroCozinha {

    public static final String MSG_COZIHA_NAO_ENCONTRADA
            = "não existe um cadastro de cozinha com o código %d";

    public static final String MSG_COZINHA_EM_USO
            = "cozinha de codigo %d não pode ser removida , pois está em uso";


    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }



    public void excluir(Long cozinhaid){
        buscarOuFalhar(cozinhaid);
        try {
            cozinhaRepository.deleteById(cozinhaid);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(String.format(MSG_COZIHA_NAO_ENCONTRADA,cozinhaid));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO,cozinhaid));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(String.format(MSG_COZIHA_NAO_ENCONTRADA,cozinhaId)));
    }
}
