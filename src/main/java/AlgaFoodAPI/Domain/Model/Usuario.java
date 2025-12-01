package AlgaFoodAPI.Domain.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String senha;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;


    @ManyToMany
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private List<Grupo> grupos;


    @ManyToMany
    @JoinTable(
            name = "restaurante_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurante_id")
    )
    private List<Restaurante> restaurantes;
}
