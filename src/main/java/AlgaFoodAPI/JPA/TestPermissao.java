package AlgaFoodAPI.JPA;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Model.Permissao;
import AlgaFoodAPI.Domain.Repository.PermissaoRepository;
import AlgaFoodAPI.Infrastructure.Repository.PermissaoRepositoryImplamentation;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TestPermissao {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        Permissao permissao = new Permissao();
        PermissaoRepository repo = applicationContext.getBean(PermissaoRepositoryImplamentation.class);

        System.out.println("Adicionando permissao: ");
        System.out.println(repo.Adicionar(permissao));
        System.out.println("buscando permissao: ");
        System.out.println(repo.buscar(permissao.getId()));
        List<Permissao>lista = repo.listar();
        System.out.println("Lista: ");
        for(Permissao p:lista){
            System.out.println(p);
        }
        repo.remover(permissao);

    }
}
