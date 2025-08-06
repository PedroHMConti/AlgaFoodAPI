//package AlgaFoodAPI.JPA;
//
//import AlgaFoodAPI.AlgaFoodApiApplication;
//import AlgaFoodAPI.Domain.Model.Cozinha;
//import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.util.List;
//
//public class ConsultaCozinhaMain {
//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
//
//        List<Cozinha> todasCozinhas = cozinhaRepository.listar();
//
//        for (Cozinha cozinha : todasCozinhas) {
//            System.out.println(cozinha.getNome());
//        }
//    }
//
//}
