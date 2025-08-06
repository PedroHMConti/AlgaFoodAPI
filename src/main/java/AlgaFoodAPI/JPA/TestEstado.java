//package AlgaFoodAPI.JPA;
//
//import AlgaFoodAPI.AlgaFoodApiApplication;
//import AlgaFoodAPI.Domain.Model.Estado;
//import AlgaFoodAPI.Domain.Repository.EstadoRepository;
//import AlgaFoodAPI.Infrastructure.Repository.EstadoRepositoryImplamantation;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.util.List;
//
//public class TestEstado {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        Estado estado = new Estado();
//        estado.setNome("Distrito Federal");
//        estado.setId(10L);
//        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
//        estadoRepository.adicionar(estado);
//        System.out.println("estado adicionado");
//        estadoRepository.remover(estado);
//        System.out.println("Estado removido");
//    }
//}
