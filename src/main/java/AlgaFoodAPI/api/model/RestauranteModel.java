package AlgaFoodAPI.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteModel {

    private Long id;

    private String nome;

    private BigDecimal taxaFrente;

    @JsonIgnore
    private CozinhaModel cozinha;
}
