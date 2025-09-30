package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
