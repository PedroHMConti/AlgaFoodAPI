package AlgaFoodAPI.Domain.Model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Embeddable
@Data
public class Endereco {


    @NotBlank
    @Column(name = "endereco_cep")
    private String cep;

    @NotBlank
    @Column(name = "endereco_logradouro")
    private String logadouro;

    @NotBlank
    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    @Valid
    private Cidade cidade;
}
