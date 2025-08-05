package AlgaFoodAPI.Infrastructure.Repository;

import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.Repository.PermissaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissaoRepositoryImplamentation implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao",Permissao.class).getResultList();
    }

    @Override
    @Transactional
    public Permissao Adicionar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class,id);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
        System.out.println("permissao removida");
    }
}
