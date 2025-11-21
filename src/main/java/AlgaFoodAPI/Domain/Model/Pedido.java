package AlgaFoodAPI.Domain.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal subtotal;
    @NotNull
    private BigDecimal taxaFrete;
    @NotNull
    private BigDecimal valorTotal;

    @JsonIgnore
    @Embedded
    private Endereco enderecoEntrega;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPedido status;


    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @JsonIgnore
    private LocalDateTime dataConfirmacao;
    @JsonIgnore
    private LocalDateTime dataCancelamento;
    @JsonIgnore
    private LocalDateTime dataEntrega;


    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaDePagamento formaPagamento;

    @JsonIgnore
    @JsonIgnoreProperties({"taxaFrete","dataCadastro","dataAtualizacao","endereco","cozinha","formasDePagamento","produtos","usuarios"})
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @JsonIgnoreProperties({"email","senha","dataCadastro","grupos","restaurantes"})
    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

}
