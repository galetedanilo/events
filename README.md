# CadClient

# Sobre o projeto

CadClient é um aplicação back end desenvolvida com Spring Boot para implementar um CRUD completo de web services REST para acessar um recurso de clientes, que foi solicitado como um trabalho de final do primeiro capítulo do Bootcamp Spring React da DevSuperior.

# Enunciado do trabalho

Implemente as funcionalidades necessárias para que os testes do projeto abaixo passem:

```
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerDataIntegrityIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deleteShouldReturnBadRequestWhenDependentId() throws Exception {		

		Long dependentId = 1L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", dependentId));
				
		result.andExpect(status().isBadRequest());
	}
}

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CityControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deleteShouldReturnNoContentWhenIndependentId() throws Exception {		
		
		Long independentId = 5L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", independentId));
		
		
		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteShouldReturnNotFoundWhenNonExistingId() throws Exception {		

		Long nonExistingId = 50L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", nonExistingId));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/cities")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].name").value("Belo Horizonte"));
		result.andExpect(jsonPath("$[1].name").value("Belém"));
		result.andExpect(jsonPath("$[2].name").value("Brasília"));
	}
}

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EventControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void updateShouldUpdateResourceWhenIdExists() throws Exception {

		long existingId = 1L;
		
		EventDTO dto = new EventDTO(null, "Expo XP", LocalDate.of(2021, 5, 18), "https://expoxp.com.br", 7L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(put("/events/{id}", existingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(1L));		
		result.andExpect(jsonPath("$.name").value("Expo XP"));
		result.andExpect(jsonPath("$.date").value("2021-05-18"));
		result.andExpect(jsonPath("$.url").value("https://expoxp.com.br"));
		result.andExpect(jsonPath("$.cityId").value(7L));
	}

	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		long nonExistingId = 1000L;
		
		EventDTO dto = new EventDTO(null, "Expo XP", LocalDate.of(2021, 5, 18), "https://expoxp.com.br", 7L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(put("/events/{id}", nonExistingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
}

```

O projeto deverá estar com um ambiente de testes configurado acessando o banco de dados H2, deverá usar Maven como gerenciador de dependência e Java 11 como linguagem

# Modelo Conceitual

![Modelo](https://github.com/galetedanilo/cadclient/blob/master/assets/modelo.png)

# Padrão Camada

![Padrao](https://github.com/galetedanilo/cadclient/blob/master/assets/camadas.png)

# Tecnologias Utilizadas

## Back end

- Java
- Spring Boot
- JPA / Hibernate
- Maven
- JUnit
- Mockito

# Como Executar o Projeto

## Back end:

Pré-requisito: Java 11

```bash
# clonar repositório
git clone https://github.com/galetedanilo/events.git

# entrar na pasta do back end
cd backend

# executar projeto
./mvnw spring-boot:run
```

# Agradecimentos

-  Equipe DevSuperior
-  Prof. Nélio Alves

# Autor

Danilo Fernandes Galete

https://www.linkedin.com/in/galetedanilo/

