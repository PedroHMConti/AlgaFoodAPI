package AlgaFoodAPI.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

    @NotNull
    private String nome;
}
