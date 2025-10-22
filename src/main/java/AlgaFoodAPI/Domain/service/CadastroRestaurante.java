package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.RestauranteNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CadastroRestaurante {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinha cadastroCozinha;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaid = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaid);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long restauranteId){
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        try{
            restauranteRepository.deleteById(restauranteId);
        }catch (EmptyResultDataAccessException ex) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId){
        return restauranteRepository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe cadastro para restaurante com o código %d",restauranteId)));
    }
}
