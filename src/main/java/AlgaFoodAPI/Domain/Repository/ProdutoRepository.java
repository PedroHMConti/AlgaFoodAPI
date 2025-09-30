package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
