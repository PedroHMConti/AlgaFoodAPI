package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();
    List<Cozinha> listarPorNome(String nome);
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}
