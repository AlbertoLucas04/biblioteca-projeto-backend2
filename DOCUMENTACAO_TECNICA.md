# Projeto Biblioteca Digital - Documentação Técnica

## 📋 Visão Geral

Sistema de gerenciamento de biblioteca desenvolvido com **Spring Boot 3.1.0**, **Java 21** e **JPA/Hibernate**.

---

## 🏗️ Arquitetura

### Camadas da Aplicação

```
┌─────────────────────────────────────────┐
│      Camada de Apresentação             │
│  (Controllers - REST Endpoints)         │
├─────────────────────────────────────────┤
│      Camada de Negócio                  │
│  (Services - Lógica de Domínio)         │
├─────────────────────────────────────────┤
│      Camada de Persistência             │
│  (Repositories - JPA/Hibernate)         │
├─────────────────────────────────────────┤
│      Camada de Domínio                  │
│  (Entities - Modelos JPA)               │
├─────────────────────────────────────────┤
│      Banco de Dados                     │
│  (H2 em memória)                        │
└─────────────────────────────────────────┘
```

---

## 📚 Modelo de Dados

### Entidades e Relacionamentos

```
┌──────────────┐           ┌──────────────┐
│    USUARIO   │◄─────────►│ EMPRESTIMO   │◄─────────┐
│              │           │              │           │
│ - id         │    1:N    │ - id         │      M:1  │
│ - nome       │           │ - dataEmp    │           │
│ - email      │           │ - dataDev    │           │
│ - telefone   │           │              │           │
└──────────────┘           └──────────────┘           │
                                                       │
                           ┌──────────────┐            │
                           │     LIVRO    │            │
                           │              │            │
                           │ - id         │◄───────────┘
                           │ - titulo     │   M:1
                           │ - autor      │
                           │ - isbn       │
                           │ - anoPubl    │
                           └──────────────┘
```

### Explicação dos Relacionamentos

1. **Usuario → Emprestimo**: Um usuário pode fazer múltiplos empréstimos
2. **Livro → Emprestimo**: Um livro pode estar envolvido em múltiplos empréstimos
3. **Emprestimo**: Tabela de junção que relaciona Usuarios e Livros

---

## 🔗 Endpoints REST

### Base URL
```
http://localhost:8080
```

### Livros
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/livros` | Lista todos os livros |
| GET | `/livros/{id}` | Obter livro específico |
| POST | `/livros` | Criar novo livro |
| PUT | `/livros/{id}` | Atualizar livro |
| DELETE | `/livros/{id}` | Deletar livro |

### Usuarios
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Obter usuário específico |
| POST | `/usuarios` | Criar novo usuário |
| PUT | `/usuarios/{id}` | Atualizar usuário |
| DELETE | `/usuarios/{id}` | Deletar usuário |

### Emprestimos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/emprestimos` | Lista todos os empréstimos |
| GET | `/emprestimos/{id}` | Obter empréstimo específico |
| POST | `/emprestimos` | Criar novo empréstimo |
| PUT | `/emprestimos/{id}` | Atualizar empréstimo |
| DELETE | `/emprestimos/{id}` | Deletar empréstimo |

---

## 📝 Exemplos de Requisições

### Criar um Livro
```bash
curl -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Clean Code",
    "autor": "Robert C. Martin",
    "isbn": "978-0132350884",
    "anoPublicacao": 2008
  }'
```

### Criar um Usuário
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "telefone": "11999999999"
  }'
```

### Criar um Empréstimo
```bash
curl -X POST http://localhost:8080/emprestimos \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "livroId": 1,
    "dataEmprestimo": "2024-06-03"
  }'
```

### Listar Todos os Livros
```bash
curl -X GET http://localhost:8080/livros
```

---

## 🗄️ Banco de Dados

### Configuração
- **Tipo**: H2 (em memória)
- **DDL**: Criação automática (`create-drop`)
- **Console H2**: Disponível em `http://localhost:8080/h2-console`

### Credenciais do Console H2
- **Username**: `sa`
- **Password**: (deixe em branco)
- **JDBC URL**: `jdbc:h2:mem:testdb`

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.1.0**
- **Spring Data JPA**
- **Hibernate 6.x**
- **H2 Database**
- **Maven**

---

## 📦 Dependências Principais

```xml
<!-- Spring Boot Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Boot Web (REST) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- H2 Database -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.8+

### Passos
1. Clonar o repositório
```bash
git clone <seu-repositorio>
cd biblioteca-projeto-backend2
```

2. Compilar o projeto
```bash
mvn clean compile
```

3. Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Alternativa: Executar com Maven
```bash
mvn clean install
java -jar target/biblioteca-digital-0.0.1-SNAPSHOT.jar
```

---

## 📂 Estrutura de Pastas

```
biblioteca-projeto-backend2/
├── src/
│   ├── main/
│   │   ├── java/com/biblioteca/
│   │   │   ├── BibliotecaApplication.java
│   │   │   ├── controller/       # REST Controllers
│   │   │   ├── service/          # Business Logic
│   │   │   ├── repository/       # Data Access
│   │   │   ├── entity/           # Domain Models
│   │   │   └── dto/              # Data Transfer Objects
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Testes unitários
├── pom.xml
├── README.md
└── VERIFICACAO_ENTREGA.md
```

---

## 🔍 Validações Implementadas

### Entidades

**Livro**
- `titulo`: Obrigatório
- `autor`: Obrigatório
- `isbn`: Obrigatório, único
- `anoPublicacao`: Opcional, inteiro

**Usuario**
- `nome`: Obrigatório
- `email`: Obrigatório, único
- `telefone`: Opcional

**Emprestimo**
- `usuario_id`: Obrigatório, chave estrangeira
- `livro_id`: Obrigatório, chave estrangeira
- `dataEmprestimo`: Obrigatório
- `dataDevolucao`: Opcional

---

## 🎯 Próximos Passos

- [ ] Adicionar validações de negócio (ex: impedir devolução antes da data)
- [ ] Implementar tratamento global de exceções
- [ ] Adicionar autenticação e autorização
- [ ] Criar testes unitários e de integração
- [ ] Adicionar Swagger/OpenAPI para documentação
- [ ] Implementar paginação nos endpoints de listagem
- [ ] Adicionar relatórios de empréstimos ativos

---

## 📞 Suporte

Para dúvidas ou problemas, consulte a documentação do Spring Boot:
https://spring.io/projects/spring-boot

---

**Versão**: 0.0.1-SNAPSHOT  
**Última Atualização**: 03 de Junho de 2026
