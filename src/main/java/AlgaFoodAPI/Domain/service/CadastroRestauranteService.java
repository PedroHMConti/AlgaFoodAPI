package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaid = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaid).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não existe um cadastro de cozinha com o código %d",cozinhaid)));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long restauranteId){
        try{
            restauranteRepository.deleteById(restauranteId);
        }catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("não existe um cadastro de cozinha com o código %d",restauranteId));
        }
    }
}
