package com.conti.AlgaFoodAPI;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlgaFoodApiApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;


	@BeforeEach
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port=port;

		flyway.migrate();
	}


	@Test
	public void deve_retornar_status200_ao_consultar_cozinhas(){

				given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.statusCode(HttpStatus.OK.value());
	}
	@Test
	public void deve_conter_4_cozinhas_e_retornar_status200_ao_consultar_cozinhas(){
				given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(4)).body("nome", Matchers.hasItems("Brasileira","Tailandesa"));
	}

	@Test
	public void deve_retornar_status_201_quando_cadastrar_cozinha(){
		given()
				.accept(ContentType.JSON)
				.body("{\"nome\" : \"cinhesa\" }")
				.contentType(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deve_retornar_status_correto_e_resposta_correta_ao_consultar_cozinha_existente(){
		given()
				.pathParam("cozinhaId",2)
				.accept(ContentType.JSON)
				.when()
				.get("{cozinhaId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome",equalTo("Indiana"));
	}
	@Test
	public void deve_retornar_404_ao_consultar_cozinha_inexistente(){
		given()
				.pathParam("cozinhaId",1013813)
				.accept(ContentType.JSON)
				.when()
				.get("{cozinhaId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}




}