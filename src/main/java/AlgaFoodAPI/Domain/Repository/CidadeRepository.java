package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade adicionar(Cidade cidade);
    void remover(Cidade cidade);

}
