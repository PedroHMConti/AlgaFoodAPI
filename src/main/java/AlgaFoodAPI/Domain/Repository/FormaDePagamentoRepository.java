package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.FormaDePagamento;

import java.util.List;

public interface FormaDePagamentoRepository {
    List<FormaDePagamento> listar();
    FormaDePagamento buscar(Long id);
    FormaDePagamento adicionar(FormaDePagamento formaDePagamento);
    void remover(FormaDePagamento formaDePagamento);
}
