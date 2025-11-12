package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
//import AlgaFoodAPI.Domain.Exception.RestauranteNaoEncontradoException;
import AlgaFoodAPI.Domain.Exception.RestauranteNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CadastroRestaurante {

    public static final String MSG_RESTAURANTE_NOT_FOUND = "não existe cadastro para restaurante com o código %d";

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private CadastroCozinha cadastroCozinha;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaid = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaid);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long restauranteId){
        try{
            restauranteRepository.deleteById(restauranteId);
        }catch (EmptyResultDataAccessException ex) {
            throw new RestauranteNaoEncontradoException(String.format(MSG_RESTAURANTE_NOT_FOUND,restauranteId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("o restaurante  de codigo %d não pode ser removida , pois está em uso",restauranteId));
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId){
        return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(String.format(MSG_RESTAURANTE_NOT_FOUND,restauranteId)));
    }

    @Transactional
    public void ativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.setAtivo(true);
    }
    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.setAtivo(false);
    }
}
