package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Optional<Pedido> findByCodigo(String codigo);

    void deleteByCodigo(String codigo);
}
