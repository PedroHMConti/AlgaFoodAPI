package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao Adicionar(Permissao permissao);
    Permissao buscar(Long id);
    void remover(Permissao permissao);
}
