Implement the requested change following the project's clean architecture strictly.

Before writing any code:
1. Read CLAUDE.md to orient yourself — it documents the architecture, domain concepts, migration paths, shared utilities, and environment setup. Only explore individual files when CLAUDE.md does not cover the specific detail you need.
2. Identify which feature module is affected
3. Map out which layers need changes: domain entity, gateway interface, use case, infrastructure adapter, mapper, DTO, controller, migration
4. If a database change is needed, determine the correct migration directory and next available version number

Implementation rules:
- Domain layer: pure Java `record`s, no framework annotations, no JPA
- Use cases wired via `@Configuration` in `infrastructure/beans/` — never `@Component` on implementations
- MapStruct mappers: interfaces only, no manual mapping logic
- Flyway migrations: place in the correct module subdirectory, follow the version sequence spaced by 10
- If a new `agenciesModel/` subdirectory is created, add it to `flyway.locations` in `application.yaml`
- `hibernate.ddl-auto` stays `validate` — never touch it
- No comments unless the WHY is non-obvious; no docstrings

After implementation:
- Confirm the change compiles (`./mvnw package -DskipTests`)
- If a migration was added, verify Flyway can apply it against the running database
- List every file modified or created