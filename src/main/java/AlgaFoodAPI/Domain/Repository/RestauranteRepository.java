package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {
    int countByCozinhaId(Long cozinhaId);


}
