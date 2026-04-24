# Health Manager API

API REST para gerenciamento de saúde, conectando clientes a coaches e nutricionistas, com registro de biometria, histórico de saúde, estilo de vida, treinos, pagamentos e redes sociais.

> Coleção Postman disponível em `health-manager.postman_collection.json` na raiz do projeto.

---

## Sumário

- [Stack](#stack)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Executando a Aplicação](#executando-a-aplicação)
- [Endpoints da API](#endpoints-da-api)
- [Modelo de Domínio](#modelo-de-domínio)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Migrações de Banco de Dados](#migrações-de-banco-de-dados)
- [Testes](#testes)

---

## Stack

| Tecnologia      | Versão |
|-----------------|--------|
| Java            | 24     |
| Spring Boot     | 4.0.5  |
| PostgreSQL      | —      |
| Flyway          | —      |
| MapStruct       | 1.6.3  |
| Spring Data JPA | —      |

---

## Pré-requisitos

- **Java 24+** instalado
- **PostgreSQL** rodando em `localhost:5432`
- **Maven Wrapper** (`./mvnw`) incluso no projeto

---

## Configuração do Ambiente

### 1. Banco de dados

```sql
CREATE DATABASE "health-manager-db";
```

O usuário padrão esperado é `postgres`.

### 2. Variável de ambiente

```bash
# Linux / macOS
export POSTGRES_PASSWORD=sua_senha

# Windows (PowerShell)
$env:POSTGRES_PASSWORD = "sua_senha"

# Ou crie um arquivo .env na raiz
POSTGRES_PASSWORD=sua_senha
```

### 3. Configuração padrão (`application.yaml`)

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/health-manager-db
    username: postgres
    password: ${POSTGRES_PASSWORD}
```

---

## Executando a Aplicação

```bash
./mvnw spring-boot:run

./mvnw package -DskipTests
java -jar target/manager-*.jar
```

O Flyway aplicará as migrações automaticamente ao iniciar. A API estará em `http://localhost:8080`.

---

## Endpoints da API

Base URL: `http://localhost:8080/api/v1/users`

---

### Usuários

#### `GET /` — Listar usuários (apenas ativos, paginado)

Query params: `page`, `size`, `sort`

```
GET /api/v1/users/?page=0&size=10&sort=name,asc
```

**Resposta 200:**
```json
{
  "content": [
    {
      "id": "uuid",
      "name": "João Silva",
      "email": "joao@email.com",
      "role": "CLIENT",
      "createdAt": "2024-01-15T10:30:00",
      "updatedAt": "2024-01-15T10:30:00"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "number": 0
}
```

---

#### `GET /{id}` — Buscar usuário por ID

**Resposta 200:**
```json
{
  "id": "uuid",
  "name": "João Silva",
  "email": "joao@email.com",
  "role": "CLIENT",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00",
  "client": {
    "id": "uuid",
    "coachId": "uuid",
    "coachName": "Carlos Coach",
    "nutritionistId": null,
    "nutritionistName": null,
    "socialMedia": [
      { "id": "uuid", "platform": "INSTAGRAM", "handle": "@joao.silva", "createdAt": "..." }
    ],
    "createdAt": "...",
    "updatedAt": "..."
  },
  "professional": null
}
```

---

#### `POST /create` — Criar usuário

```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123",
  "role": "PROFESSIONAL"
}
```

**Valores válidos para `role`:** `ADMIN` · `PROFESSIONAL` · `CLIENT` · `TESTER`

**Resposta 201:** `UsersResponse`

---

#### `PUT /{id}` — Atualizar usuário

Campos opcionais — envia apenas o que deseja alterar.

```json
{
  "name": "Novo Nome",
  "email": "novo@email.com"
}
```

**Resposta 200:** `UsersResponse`

---

#### `DELETE /{id}` — Deletar usuário (soft delete)

Marca o usuário como inativo (`active = false`). Usuários inativos não aparecem na listagem.

**Resposta 204**

---

#### `PATCH /{id}/password` — Alterar senha

```json
{
  "currentPassword": "senha123",
  "newPassword": "novaSenha456"
}
```

**Resposta 204**

---

### Perfil — Cliente

#### `POST /{id}/client` — Atribuir perfil de cliente a um usuário existente

`coachId` e `nutritionistId` são opcionais. Um cliente pode ter coach, nutricionista, ambos ou nenhum.

```json
{
  "coachId": "uuid",
  "nutritionistId": null
}
```

**Resposta 201:** `UserDetailsResponse`

---

#### `POST /{professionalId}/register-client` — Profissional cadastra um cliente

Cria automaticamente o usuário e o perfil de cliente vinculado ao profissional. O tipo do vínculo (coach ou nutricionista) é determinado pelo `ProfessionalType` do profissional.

```json
{
  "name": "Maria Cliente",
  "email": "maria@email.com"
}
```

**Resposta 201:**
```json
{
  "userId": "uuid",
  "name": "Maria Cliente",
  "email": "maria@email.com",
  "temporaryPassword": "a3f9bc12de"
}
```

> `temporaryPassword` é retornado temporariamente até o envio por e-mail ser implementado.

---

### Perfil — Profissional

#### `POST /{id}/professional` — Atribuir perfil de profissional a um usuário existente

```json
{
  "type": "COACH",
  "registrationType": "CREF",
  "registrationId": "123456-G/SP",
  "specialty": "Musculação e Hipertrofia"
}
```

**Valores válidos para `type`:** `COACH` · `NUTRITIONIST`

**Valores válidos para `registrationType`:** `CREF` · `CRN`

**Resposta 201:** `ProfessionalResponse`

---

#### `PUT /{id}/professional` — Atualizar perfil de profissional

Todos os campos são opcionais.

```json
{
  "specialty": "Musculação, Hipertrofia e Emagrecimento",
  "registrationId": "654321-G/SP"
}
```

**Resposta 200:** `ProfessionalResponse`

---

### Redes Sociais

#### `POST /{id}/client/social-media` — Adicionar rede social ao cliente

```json
{
  "platform": "INSTAGRAM",
  "handle": "@joao.silva"
}
```

**Plataformas válidas:** `INSTAGRAM` · `X` · `WHATSAPP` · `FACEBOOK` · `TIKTOK` · `YOUTUBE` · `LINKEDIN`

Múltiplas entradas por perfil são permitidas, inclusive mais de uma da mesma plataforma.

**Resposta 201:** `SocialMediaResponse`

---

#### `POST /{id}/professional/social-media` — Adicionar rede social ao profissional

```json
{
  "platform": "WHATSAPP",
  "handle": "+5511999999999"
}
```

**Resposta 201:** `SocialMediaResponse`

---

#### `DELETE /social-media/{socialMediaId}` — Remover rede social

**Resposta 204**

---

## Modelo de Domínio

```
Users (entidade central)
 ├── OneToOne → Clients
 │    ├── ManyToOne → Professionals (coach)       — opcional
 │    ├── ManyToOne → Professionals (nutritionist) — opcional
 │    ├── OneToMany → SocialMedia
 │    ├── OneToOne  → Biometrics
 │    ├── OneToOne  → HealthRecords
 │    │    └── OneToMany → Medications
 │    ├── OneToOne  → Lifestyles
 │    ├── OneToOne  → PhysicalTraining
 │    ├── OneToOne  → FinancialConfig
 │    └── OneToMany → Payments
 └── OneToOne → Professionals
      ├── OneToMany → SocialMedia
      ├── OneToMany → Clients (como coach)
      ├── OneToMany → Clients (como nutritionist)
      └── OneToMany → Payments (recebidos)
```

### Enums

| Enum | Valores |
|------|---------|
| `Role` | `ADMIN` · `PROFESSIONAL` · `CLIENT` · `TESTER` |
| `ProfessionalType` | `COACH` · `NUTRITIONIST` |
| `RegistrationType` | `CREF` · `CRN` |
| `PaymentStatus` | `PENDING` · `PAID` · `OVERDUE` |
| `SocialPlatform` | `INSTAGRAM` · `X` · `WHATSAPP` · `FACEBOOK` · `TIKTOK` · `YOUTUBE` · `LINKEDIN` |

---

## Estrutura do Projeto

```
src/main/java/com/health/manager/
├── ManagerApplication.java
├── shared/enums/
│   ├── Role.java
│   ├── ProfessionalType.java
│   ├── RegistrationType.java
│   ├── PaymentStatus.java
│   └── SocialPlatform.java
├── users/
│   ├── controller/
│   │   └── UsersController.java
│   ├── dto/
│   │   ├── request/
│   │   │   ├── CreateUserRequest.java
│   │   │   ├── UpdateUserRequest.java
│   │   │   ├── ChangePasswordRequest.java
│   │   │   ├── AssignClientProfileRequest.java
│   │   │   ├── AssignProfessionalProfileRequest.java
│   │   │   ├── UpdateProfessionalRequest.java
│   │   │   ├── RegisterClientRequest.java
│   │   │   └── AddSocialMediaRequest.java
│   │   └── response/user/
│   │       ├── UsersResponse.java
│   │       ├── UserDetailsResponse.java
│   │       ├── ClientResponse.java
│   │       ├── ProfessionalResponse.java
│   │       ├── RegisterClientResponse.java
│   │       └── SocialMediaResponse.java
│   ├── entity/
│   │   ├── Users.java
│   │   ├── Clients.java
│   │   ├── Professionals.java
│   │   └── SocialMedia.java
│   ├── mapper/user/
│   │   ├── CreateUserMapper.java
│   │   ├── UsersMapper.java
│   │   ├── UserDetailsMapper.java
│   │   └── ProfessionalMapper.java
│   ├── repository/
│   │   ├── UsersRepository.java
│   │   ├── ClientsRepository.java
│   │   ├── ProfessionalsRepository.java
│   │   └── SocialMediaRepository.java
│   └── service/
│       ├── CreateUserService.java
│       ├── FindUsersService.java
│       ├── FindUserDetailsService.java
│       ├── UpdateUserService.java
│       ├── DeleteUserService.java
│       ├── ChangePasswordService.java
│       ├── AssignClientProfileService.java
│       ├── AssignProfessionalProfileService.java
│       ├── UpdateProfessionalService.java
│       ├── RegisterClientService.java
│       ├── AddClientSocialMediaService.java
│       ├── AddProfessionalSocialMediaService.java
│       └── DeleteSocialMediaService.java
└── anamnesis/entity/
    ├── Biometrics.java
    ├── HealthRecords.java
    ├── Medications.java
    ├── Lifestyles.java
    ├── PhysicalTraining.java
    ├── FinancialConfig.java
    └── Payments.java

src/main/resources/
├── application.yaml
└── db/migration/
    ├── V1__create_initial_tables.sql
    └── V2__create_social_media.sql
```

---

## Migrações de Banco de Dados

O schema é gerenciado exclusivamente pelo **Flyway**. O Hibernate está configurado com `ddl-auto: validate` — ele **não altera o schema**, apenas valida.

| Arquivo | Conteúdo |
|---------|----------|
| `V1__create_initial_tables.sql` | `users`, `professionals`, `clients`, `biometrics`, `health_records`, `medications`, `lifestyles`, `physical_training`, `payments`, `financial_config` |
| `V2__create_social_media.sql` | `social_media` com CHECK constraint garantindo vínculo exclusivo (client ou professional) |

As migrações são aplicadas automaticamente ao iniciar a aplicação.

---

## Testes

```bash
# Rodar todos os testes
./mvnw test

# Rodar uma classe específica
./mvnw test -Dtest=NomeDaClasseTest
```

Os testes utilizam **Testcontainers** para subir um PostgreSQL real em container, garantindo fidelidade ao ambiente de produção.

> Certifique-se de ter o **Docker** instalado e rodando para os testes com Testcontainers.
