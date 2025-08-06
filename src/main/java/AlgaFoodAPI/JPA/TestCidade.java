//package AlgaFoodAPI.JPA;
//
//import AlgaFoodAPI.AlgaFoodApiApplication;
//import AlgaFoodAPI.Domain.Model.Cidade;
//import AlgaFoodAPI.Domain.Repository.CidadeRepository;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.util.List;
//
//public class TestCidade {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        Cidade cidade = new Cidade();
//        cidade.setNome("miami");
//        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
//        System.out.println(cidadeRepository.adicionar(cidade));
//        List<Cidade> cidades = cidadeRepository.listar();
//        System.out.println("lista de cidades : ");
//        for(Cidade cidade1 : cidades){
//            System.out.println(cidade);
//        }
//    }
//
//}
