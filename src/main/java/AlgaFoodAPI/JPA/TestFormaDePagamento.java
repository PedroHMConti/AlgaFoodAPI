//package AlgaFoodAPI.JPA;
//
//import AlgaFoodAPI.AlgaFoodApiApplication;
//import AlgaFoodAPI.Domain.Model.FormaDePagamento;
//import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.util.List;
//
//public class TestFormaDePagamento {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//
//        FormaDePagamento formaDePagamento = new FormaDePagamento();
//        FormaDePagamentoRepository repository = applicationContext.getBean(FormaDePagamentoRepository.class);
//        formaDePagamento.setDescricao("cartao de crédito");
//        System.out.println("adicionado: ");
//        System.out.println(repository.adicionar(formaDePagamento));
//        System.out.println("buscar o id = 1");
//        System.out.println(repository.buscar(1L));
//        System.out.println("lista: ");
//        List<FormaDePagamento>lista = repository.listar();
//        for(FormaDePagamento f : lista){
//            System.out.println(f);
//        }
//    }
//}
