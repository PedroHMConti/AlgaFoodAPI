package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado adicionar(Estado estado);
    void remover(Estado estado);
}
