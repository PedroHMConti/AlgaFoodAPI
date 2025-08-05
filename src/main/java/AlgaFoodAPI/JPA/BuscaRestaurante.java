package AlgaFoodAPI.JPA;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext;

public class BuscaRestaurante {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        System.out.println(restauranteRepository.buscar(1L));


    }

}
