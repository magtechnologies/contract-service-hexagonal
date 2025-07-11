Claro, aqui está um `README.md` completo e bem estruturado para a sua API, com base na implementação fornecida.

# Contract Service - Arquitetura Hexagonal

## 📋 Descrição

API RESTful para cadastro e consulta de contratos e clientes, implementada com **arquitetura hexagonal** (*Ports & Adapters*) para garantir um design de software desacoplado, testável e de fácil manutenção. O projeto utiliza **Spring Boot** e as melhores práticas de desenvolvimento de software.

-----

## ✨ Features

- **Gerenciamento de Clientes**: CRUD completo para clientes, com busca por ID e documento.
- **Gerenciamento de Contratos**: CRUD completo para contratos, com filtros por status e cliente.
- **Validação de Dados**: Validações robustas para garantir a integridade dos dados de entrada.
- **Tratamento de Exceções**: Tratamento global de exceções para fornecer respostas de erro claras.
- **Documentação da API**: Documentação interativa com Swagger/OpenAPI 3.
- **Testes Unitários**: Cobertura de testes para as camadas de domínio, infraestrutura e web.

-----

## 🏗️ Arquitetura Hexagonal

A arquitetura deste projeto é baseada no padrão **Hexagonal (Ports & Adapters)**, que isola a lógica de negócios central (domínio) das dependências externas, como frameworks web e bancos de dados.

- **`domain`**: O núcleo da aplicação. Contém os modelos de negócio (`Contract`, `Client`), as portas de entrada (`UseCase`) e as portas de saída (`Repository`). É totalmente independente de qualquer framework.
- **`infrastructure`**: A camada externa. Contém as implementações concretas das portas (adapters), como os controladores REST (adapters de entrada) e os repositórios JPA (adapters de saída).

<!-- end list -->

```
src
└── main
    └── java
        └── com/example/contractservice
            ├── domain/                   # Hexagonal (Core)
            │   ├── model/
            │   ├── port/
            │   │   ├── in/               # Portas de Entrada (Use Cases)
            │   │   └── out/              # Portas de Saída (Repositórios)
            │   └── service/              # Implementação dos Use Cases
            └── infrastructure/           # Adapters (Frameworks & Ferramentas)
                ├── adapter/
                │   ├── in/               # Adapters de Entrada
                │   │   └── web/
                │   └── out/              # Adapters de Saída
                │       └── persistence/
                ├── config/
                └── shared/               # Componentes compartilhados
```

-----

## 🛠️ Stack Tecnológica

- **Java 17**: Versão mais recente do Java com suporte de longo prazo (LTS).
- **Spring Boot 3.x**: Framework para criar aplicações robustas e independentes.
- **Spring Data JPA**: Facilita a implementação de repositórios baseados em JPA.
- **H2 Database**: Banco de dados em memória, ideal para desenvolvimento e testes.
- **Swagger/OpenAPI 3**: Para documentação interativa da API.
- **Lombok**: Reduz o código boilerplate (getters, setters, construtores).
- **MapStruct**: Simplifica a conversão entre DTOs, entidades e modelos de domínio.
- **JUnit 5 & Mockito**: Para testes unitários e de integração.
- **Maven**: Gerenciador de dependências e build.
- **GitHub Actions**: Para integração contínua (CI).

-----

## 🚀 Como Executar

### Pré-requisitos

- **JDK 17** ou superior
- **Maven 3.8** ou superior

### Passos para Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/contract-service-hexagonal.git
    cd contract-service-hexagonal
    ```

2.  **Execute a aplicação com o Maven Wrapper:**

    - No Linux/macOS:
      ```bash
      ./mvnw spring-boot:run
      ```
    - No Windows:
      ```bash
      mvnw.cmd spring-boot:run
      ```

3.  **A aplicação estará disponível em `http://localhost:8080/api`**.

### Acessos Úteis

- **Swagger UI (Documentação da API):**
  [http://localhost:8080/api/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/api/swagger-ui.html)

- **H2 Console (Banco de Dados):**
  [http://localhost:8080/api/h2-console](https://www.google.com/search?q=http://localhost:8080/api/h2-console)

    - **JDBC URL:** `jdbc:h2:mem:contractdb`
    - **User Name:** `sa`
    - **Password:** `password`

-----

## 📋 Endpoints da API

A URL base para todos os endpoints é `http://localhost:8080/api`.

### Clientes (`/clients`)

| Método | Endpoint                    | Descrição                          |
| :----- | :-------------------------- | :--------------------------------- |
| `POST` | `/`                         | Cria um novo cliente.              |
| `GET`   | `/`                         | Lista todos os clientes.           |
| `GET`   | `/{id}`                     | Busca um cliente pelo ID.          |
| `GET`   | `/document/{document}`      | Busca um cliente pelo documento.   |
| `PUT`   | `/{id}`                     | Atualiza um cliente existente.     |
| `DELETE`| `/{id}`                     | Remove um cliente.                 |

### Contratos (`/contracts`)

| Método | Endpoint                    | Descrição                          |
| :----- | :-------------------------- | :--------------------------------- |
| `POST` | `/`                         | Cria um novo contrato.             |
| `GET`   | `/`                         | Lista todos os contratos.          |
| `GET`   | `?status={status}`          | Lista contratos por status.        |
| `GET`   | `?clientId={clientId}`      | Lista contratos por ID do cliente. |
| `GET`   | `/{id}`                     | Busca um contrato pelo ID.         |
| `PUT`   | `/{id}`                     | Atualiza um contrato existente.    |
| `DELETE`| `/{id}`                     | Remove um contrato.                |

-----

## 🧪 Testes

Para garantir a qualidade e a estabilidade da aplicação, o projeto conta com uma suíte de testes unitários.

**Para executar os testes, utilize o seguinte comando:**

```bash
./mvnw test
```

Os testes cobrem as seguintes camadas:

- **`domain/service`**: Testa a lógica de negócio e os casos de uso.
- **`infrastructure/adapter/in/web`**: Testa os controladores REST.
- **`infrastructure/adapter/out/persistence`**: Testa a integração com o banco de dados.

-----

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](https://www.google.com/search?q=LICENSE) para mais detalhes.