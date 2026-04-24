# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the application
./mvnw spring-boot:run

# Build (skip tests)
./mvnw package -DskipTests

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=ManagerApplicationTests
```

The app requires `POSTGRES_PASSWORD` set as an environment variable (or in a `.env` file). The database must be running at `localhost:5432` with database name `health-manager-db` and user `postgres`.

## Architecture

**Stack:** Spring Boot 4.0 · Java 24 · PostgreSQL · Flyway · MapStruct · Spring Data JPA

### Package structure

All code lives under `com.health.manager`, organized into feature packages:

- `users/` — user management (controller, services, repositories, DTOs, mappers, entities)
- `anamnesis/` — health profile entities (no controller yet)
- `shared/enums/` — shared enums (`Role`, `ProfessionalType`, `RegistrationType`, `PaymentStatus`, `SocialPlatform`)

### Domain model

`Users` is the central entity. Every user has a `Role` and `active` flag, and optionally links to `Clients` or `Professionals` via `@OneToOne`.

**Clients** hold the full health profile: `Biometrics`, `HealthRecords` (with `Medications`), `Lifestyles`, `PhysicalTraining`, `FinancialConfig`, and `Payments`. Each is a separate `@OneToOne` entity cascaded from `Clients`. A client can have an optional `coach` (ManyToOne → Professionals) and/or an optional `nutritionist` (ManyToOne → Professionals).

**Professionals** can be coaches or nutritionists (typed via `ProfessionalType`) and hold collections of clients they serve and payments they receive.

**SocialMedia** belongs to either a `Clients` or `Professionals` (exactly one, enforced by DB CHECK constraint). Multiple entries per profile allowed — one per platform or multiple of the same platform.

### Request flow

`UsersController` (`api/v1/users`) → one service per operation → repository (Spring Data). Each operation has a dedicated service class.

DTOs and entity conversion use **MapStruct** interfaces in `users/mapper/`. Each mapper interface is annotated `@Mapper(componentModel = "spring")` and generates an implementation at compile time.

### Services

| Service | Responsibility |
|---------|----------------|
| `CreateUserService` | Creates a user with role and password |
| `FindUsersService` | Paginated list of active users |
| `FindUserDetailsService` | Single user with client/professional profiles |
| `UpdateUserService` | Partial update of name and/or email |
| `DeleteUserService` | Soft delete — sets `active = false` |
| `ChangePasswordService` | Validates current password and updates it |
| `AssignClientProfileService` | Creates `Clients` profile for existing user |
| `AssignProfessionalProfileService` | Creates `Professionals` profile for existing user |
| `UpdateProfessionalService` | Partial update of professional profile fields |
| `RegisterClientService` | Professional creates a client — generates temp password, links client to professional by type |
| `AddClientSocialMediaService` | Adds a social media entry to a client profile |
| `AddProfessionalSocialMediaService` | Adds a social media entry to a professional profile |
| `DeleteSocialMediaService` | Removes a social media entry by ID |

### Database migrations

Schema is managed exclusively by Flyway. Migration files live in `src/main/resources/db/migration/`. Hibernate is set to `ddl-auto: validate` — it will not modify the schema, only validate it against migrations.

| File | Description |
|------|-------------|
| `V1__create_initial_tables.sql` | All core tables: `users`, `professionals`, `clients`, `biometrics`, `health_records`, `medications`, `lifestyles`, `physical_training`, `payments`, `financial_config` |
| `V2__create_social_media.sql` | `social_media` table with CHECK constraint enforcing single owner |

### Conventions

- Passwords are stored in plain text — BCrypt should be added when Spring Security is integrated.
- `RegisterClientService` returns a `temporaryPassword` in the response until email sending is implemented (marked with `// TODO`).
- Postman collection is at `health-manager.postman_collection.json` in the project root.
