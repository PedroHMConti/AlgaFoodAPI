package AlgaFoodAPI.Domain.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;
}
