package AlgaFoodAPI.Infrastructure.Repository;

import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepositoryImplamentation implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager ;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade",Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class,id);
    }

    @Override
    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
