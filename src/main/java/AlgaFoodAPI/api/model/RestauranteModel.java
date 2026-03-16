package AlgaFoodAPI.api.model;

import AlgaFoodAPI.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteModel {

    @JsonView(RestauranteView.resumo.class)
    private Long id;
    @JsonView(RestauranteView.resumo.class)
    private String nome;
    @JsonView(RestauranteView.resumo.class)
    private BigDecimal taxaFrete;
    @JsonView(RestauranteView.resumo.class)
    private CozinhaModel cozinha;
}
