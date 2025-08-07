package AlgaFoodAPI.Domain.Repository;

import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento,Long> {

}
