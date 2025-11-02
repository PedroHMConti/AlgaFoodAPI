package com.conti.AlgaFoodAPI;

import AlgaFoodAPI.AlgaFoodApiApplication;
import AlgaFoodAPI.Domain.Exception.CozinhaNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Repository.CozinhaRepository;
import AlgaFoodAPI.Domain.service.CadastroCozinha;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlgaFoodApiApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;


	@Test
	public void deve_retornar_status200_ao_consultar_cozinhas(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

				given()
					.basePath("/cozinhas")
					.port(port)
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.statusCode(200);
	}
	@Test
	public void deve_conter_4_cozinhas_e_retornar_status200_ao_consultar_cozinhas(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

				given()
					.basePath("/cozinhas")
					.port(port)
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(15)).body("nome", Matchers.hasItems("Italiana","1"));
	}

}