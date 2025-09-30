package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
