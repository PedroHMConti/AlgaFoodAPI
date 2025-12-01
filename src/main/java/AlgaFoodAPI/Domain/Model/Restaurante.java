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


    @Column(name = "nome")
    private String nome;


    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;


    @CreationTimestamp
    @Column(nullable = false,columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false,columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;


    @Embedded
    private Endereco endereco;

    @ManyToOne
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
