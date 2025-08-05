package AlgaFoodAPI.JPA;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

public class AdicionaRestaurante {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante mcdonalds = new Restaurante();
        mcdonalds.setNome("McDonalds");
        mcdonalds.setTaxaFrete(BigDecimal.valueOf(10.25));
        Restaurante novo =  restauranteRepository.salvar(mcdonalds);
        System.out.println("novo restaurante é : "+ novo);
    }
}
