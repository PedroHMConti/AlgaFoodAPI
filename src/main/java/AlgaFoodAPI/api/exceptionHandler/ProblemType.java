package AlgaFoodAPI.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTRADA_DE_DADOS_INVALIDA("/erro-de-input","erro de input"),
    ERRO_DE_SISTEMA("/erro-de-sistema","erro de sistema"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","recurso não encontrado"),
    PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensível","mensagem incompreensivel"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }

}
