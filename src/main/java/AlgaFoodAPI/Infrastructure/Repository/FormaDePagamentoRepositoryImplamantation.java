package AlgaFoodAPI.Infrastructure.Repository;



import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormaDePagamentoRepositoryImplamantation implements FormaDePagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaDePagamento> listar() {
        return manager.createQuery("from FormaDePagamento",FormaDePagamento.class).getResultList();
    }

    @Override
    public FormaDePagamento buscar(Long id) {
        return manager.find(FormaDePagamento.class,id);
    }

    @Override
    @Transactional
    public FormaDePagamento adicionar(FormaDePagamento formaDePagamento) {
        return manager.merge(formaDePagamento);
    }

    @Override
    @Transactional
    public void remover(FormaDePagamento formaDePagamento) {
        formaDePagamento = buscar(formaDePagamento.getId());
        manager.remove(formaDePagamento);
    }
}
