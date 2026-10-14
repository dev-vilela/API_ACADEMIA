# MasterSys — API REST para Gerenciamento de Academia

**Uma API REST desenvolvida com Java, Spring Boot e PostgreSQL para gerenciamento de operações acadêmicas de uma academia, incluindo alunos, matrículas, modalidades, planos, faturamento e assiduidade.**

O MasterSys é um projeto de desenvolvimento back-end que aplica conceitos de arquitetura de aplicações, modelagem relacional, persistência de dados e regras de negócio. A solução foi estruturada para organizar as principais entidades do domínio e disponibilizar recursos de consulta e relatórios gerenciais.

---

## Sumário

- [Visão geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura e organização](#arquitetura-e-organização)
- [Modelagem de dados](#modelagem-de-dados)
- [Relatórios gerenciais](#relatórios-gerenciais)
- [Como executar](#como-executar)
- [Evolução do projeto](#evolução-do-projeto)

## Visão geral

O sistema foi concebido para centralizar informações relacionadas à gestão de uma academia, estruturando os dados de alunos, matrículas, modalidades esportivas, planos e cobranças em um banco de dados relacional.

A API utiliza recursos do ecossistema Spring para organizar o acesso aos dados, aplicar regras de negócio e oferecer consultas paginadas e filtradas. A estrutura também contempla projeções específicas para a construção de relatórios gerenciais.

### Objetivos técnicos

- Desenvolver APIs REST com Java e Spring Boot.
- Aplicar separação de responsabilidades na organização do código.
- Implementar persistência de dados relacionais com PostgreSQL.
- Utilizar DTOs para estruturar os dados de entrada e saída.
- Implementar consultas dinâmicas com Specifications e paginação.
- Estruturar consultas específicas para relatórios com Projections.
- Reforçar a integridade dos dados por meio de restrições no banco de dados.

## Funcionalidades

### Gestão de alunos

- Cadastro, consulta, atualização e exclusão de alunos.
- Busca por identificador.
- Filtragem dinâmica de resultados.
- Paginação de consultas.
- Validação de regras de negócio, incluindo a verificação de e-mails duplicados.

### Matrículas e modalidades

Modelagem das relações entre alunos, matrículas e modalidades contratadas, contemplando:

- Cadastro de modalidades.
- Associação de modalidades às matrículas.
- Vinculação de planos e graduações.
- Controle de status e períodos de matrícula.

### Planos e graduações

Estrutura de dados para organizar os planos comerciais e as graduações oferecidas em cada modalidade, incluindo valores mensais, ativação e regras de unicidade.

### Controle financeiro

Modelagem de faturas com informações sobre:

- Valor da cobrança.
- Data de vencimento.
- Data de pagamento.
- Data de cancelamento.
- Situação da fatura.

### Controle de assiduidade

Estrutura para registrar entradas e saídas dos alunos, permitindo armazenar o histórico de presença associado às matrículas.

## Tecnologias

| Tecnologia | Aplicação |
|---|---|
| Java | Linguagem de desenvolvimento |
| Spring Boot | Framework para construção da aplicação |
| Spring Data JPA | Persistência e acesso a dados |
| PostgreSQL | Banco de dados relacional |
| DTOs | Estruturação das requisições e respostas |
| Spring Data JPA Specifications | Construção de filtros dinâmicos |
| Pageable | Paginação dos resultados |
| Projections | Representação de dados específicos em consultas |

## Arquitetura e organização

O projeto adota uma organização em pacotes para separar componentes com responsabilidades distintas.

```text
com.spring.mastersys
├── controller
├── service
├── repository
├── domain
├── dto
├── exception
└── projection
```

**Responsabilidades dos componentes:**

- **Controller:** disponibilização dos endpoints HTTP da API.
- **Service:** implementação e coordenação das regras de negócio.
- **Repository:** acesso e persistência dos dados.
- **Domain:** representação das entidades e do domínio da aplicação.
- **DTO:** definição dos objetos utilizados na comunicação da API.
- **Exception:** organização das exceções relacionadas à aplicação e às regras de negócio.
- **Projection:** representação dos resultados específicos de consultas e relatórios.

A separação busca facilitar a manutenção, a legibilidade e a evolução do código.

## Modelagem de dados

O banco de dados contempla as seguintes entidades principais:

| Entidade | Responsabilidade |
|---|---|
| `alunos` | Armazenamento dos dados cadastrais dos alunos |
| `matriculas` | Controle das matrículas e de seus status |
| `modalidades` | Cadastro das modalidades oferecidas |
| `planos` | Definição dos planos e valores mensais |
| `graduacoes` | Registro das graduações associadas às modalidades |
| `matriculas_modalidades` | Associação entre matrículas, modalidades, planos e graduações |
| `faturas_modalidades` | Registro das cobranças e de suas situações |
| `assiduidades` | Registro dos horários de entrada e saída |

A modelagem utiliza chaves primárias, chaves estrangeiras e restrições de integridade, incluindo campos obrigatórios, unicidade e validações de valores e status.

## Relatórios gerenciais

A API contempla uma estrutura específica para consultas gerenciais, utilizando interfaces Projection para representar os resultados.

| Endpoint | Finalidade |
|---|---|
| `GET /relatorios/faturamento-mensal` | Consulta de faturamento mensal |
| `GET /relatorios/alunos-cidades` | Consulta da distribuição de alunos por cidade |
| `GET /relatorios/faturas-aberto` | Consulta de faturas em aberto |

Os resultados efetivamente disponibilizados dependem da implementação das consultas nos componentes responsáveis pelo acesso aos dados.

## Como executar

### Pré-requisitos

- JDK compatível com a versão Java configurada no projeto.
- PostgreSQL.
- Maven, caso não esteja utilizando o Maven Wrapper do projeto.
- Git.

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_REPOSITORIO>
```

### 2. Configure o banco de dados

Crie um banco de dados no PostgreSQL e configure as credenciais da aplicação no arquivo de configuração do Spring Boot.

Exemplo de configuração em `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Defina as variáveis de ambiente correspondentes no seu ambiente de execução.

### 3. Configure o esquema

Execute os scripts SQL do projeto na ordem necessária para criar as tabelas e inserir os dados iniciais, respeitando as dependências entre as entidades.

### 4. Execute a aplicação

Com Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Se o projeto não possuir Maven Wrapper, utilize o Maven instalado:

```bash
mvn spring-boot:run
```

> Os comandos e as configurações devem ser ajustados caso a estrutura atual do projeto utilize outro processo de inicialização.

## Evolução do projeto

O MasterSys é um projeto de evolução técnica em desenvolvimento back-end, com foco na aplicação prática de Java, Spring Boot, persistência relacional e construção de APIs REST.

Entre as possibilidades de evolução estão:

- Documentação interativa com OpenAPI/Swagger.
- Testes unitários e testes de integração.
- Padronização do tratamento de erros HTTP.
- Autenticação e autorização.
- Validação de consistência entre modalidades, planos e graduações.
- Aprimoramento das regras de geração e controle de faturas.
- Conteinerização da aplicação e do banco de dados com Docker.

---

**Desenvolvido por Paulo Vilela Furtado**

Projeto pessoal para estudo e prática de desenvolvimento de software com Java e Spring Boot.
