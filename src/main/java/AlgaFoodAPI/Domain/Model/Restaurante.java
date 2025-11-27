package AlgaFoodAPI.Domain.Model;


import AlgaFoodAPI.api.Core.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurante")
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @DecimalMin("0.01")
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;


    @CreationTimestamp
    @JsonIgnore
    @Column(nullable = false,columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @JsonIgnore
    @Column(nullable = false,columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;


    @Embedded
    @Valid
    private Endereco endereco;

    @Valid
    @ManyToOne
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @JoinColumn(name = "cozinhaId",nullable = false)
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(
            name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private List<FormaDePagamento> formasDePagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;


    private Boolean ativo = Boolean.TRUE;

    @ManyToMany
    @JoinTable(
            name = "restaurante_usuario",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
}
