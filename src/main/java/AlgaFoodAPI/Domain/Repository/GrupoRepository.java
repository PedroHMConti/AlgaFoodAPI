package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {
}
