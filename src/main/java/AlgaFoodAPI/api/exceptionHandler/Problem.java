package AlgaFoodAPI.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String mensagemUsuario;

    private LocalDateTime timestamp;

    private List<Campo> campos;

    @Builder
    @Getter
    public static class Campo{
        private String nome;

        private String userMessage;
    }

}

