package AlgaFoodAPI.Domain.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    @Valid
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

}
