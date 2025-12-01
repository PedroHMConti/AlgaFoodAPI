package AlgaFoodAPI.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoInput {
    @NotBlank
    private String descricao;
}
