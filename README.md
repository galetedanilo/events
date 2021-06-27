# Events

# Sobre o projeto

Events é uma aplicação back end desenvolvida com Spring Boot para implementar um CRUD completo de web services REST para acessar os recursos de Cities, Events e Users.
Neste sistema, somente as rotas de leitura (GET) de eventos e cidades são públicas (não precisa de login). Usuários CLIENT podem também inserir (POST) novos eventos. 
Os demais acessos são permitidos apenas a usuários ADMIN. O projeto foi desenvolvido empregando o padrão TDD(Test Driven Development) e foi solicitado como um trabalho 
final do segundo e terceiro capítulo do Bootcamp Spring React da DevSuperior.

Capítulo 2: 

Fundamentos de testes automatizados:
- Tipos de testes
- Benefícios
- TDD - Test Driven Development
- Boas práticas e padrões
JUnit:
- Básico (vanilla)
JUnit Spring Boot:
- Repositories
- Services
- Resources (web)
- Integração
Mockito & MockBean:
- @Mock
- @InjectMocks
- Mockito.when / thenReturn / doNothing / doThrow
- ArgumentMatchers
- Mockito.verify
- @MockBean
- @MockMvc

Capítulo 3:

Modelo de dados de usuários e perfis:
- User
- Role
Validação com Bean Validation:
- Annotations
- Customizando a resposta HTTP
- Validações personalizadas com acesso a banco
Autenticação e autorização:
- Spring Security
- OAuth 2.0
- Token JWT
- Autorização de rotas por perfil


# Modelo Conceitual

![Modelo](https://github.com/galetedanilo/events/blob/master/assets/modelo.png)

# Padrão Camada

![Padrao](https://github.com/galetedanilo/events/blob/master/assets/camadas.png)

# Tecnologias Utilizadas

## Back end

- Java
- Spring Boot
- Spring Security
- Spring Validation
- OAuth 2.0
- Token JWT
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

