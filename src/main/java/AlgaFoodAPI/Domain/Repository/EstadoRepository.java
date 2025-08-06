package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long> {

}

