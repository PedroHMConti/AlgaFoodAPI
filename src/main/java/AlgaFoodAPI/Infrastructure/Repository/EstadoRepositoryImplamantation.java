package AlgaFoodAPI.Infrastructure.Repository;

import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EstadoRepositoryImplamantation implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Estado> listar(){
        return manager.createQuery("from Estado",Estado.class).getResultList();
    }
    @Override
    public Estado buscar(Long id){
        return manager.find(Estado.class,id);
    }
    @Override
    @Transactional
    public Estado adicionar(Estado estado){
        return manager.merge(estado);
    }
    @Override
    @Transactional
    public void remover(Estado estado){
        estado = buscar(estado.getId());
        manager.remove(estado);
    }


}
