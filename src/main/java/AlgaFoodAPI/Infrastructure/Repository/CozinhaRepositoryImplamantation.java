package AlgaFoodAPI.Infrastructure.Repository;

import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.EmptyStackException;
import java.util.List;

@Repository
public class CozinhaRepositoryImplamantation implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){

        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha",Cozinha.class);

        return query.getResultList();
    }

    @Override
    public List<Cozinha> listarPorNome(String nome) {
        return manager.createQuery("from Cozinha where nome like :nome",Cozinha.class)
                .setParameter("nome","%"+nome+"%").getResultList();
    }


    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Override
    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class,id);
    }


    @Transactional
    @Override
    public void remover(Long id){
        Cozinha cozinha = buscar(id);
        if(cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }else{
            manager.remove(cozinha);
        }
    }
}
