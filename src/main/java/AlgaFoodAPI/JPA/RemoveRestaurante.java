package AlgaFoodAPI.JPA;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class RemoveRestaurante {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        restauranteRepository.remover(restauranteRepository.buscar(1L));
    }

}
