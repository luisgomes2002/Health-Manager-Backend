Review all staged and unstaged changes in this branch against the project's architectural patterns. Focus on:

**Clean Architecture compliance**
- Domain layer (`domain/entities/`, `domain/gateway/`, `domain/usercases/`) must have zero framework dependencies (no Spring annotations, no JPA)
- Domain entities must be pure Java `record`s
- Use cases must be wired via `@Configuration` beans — never `@Service` or `@Component` on use case implementations
- Infrastructure adapters implement domain gateway interfaces; domain never imports from infrastructure

**Naming and structure conventions**
- Gateway interfaces in `domain/gateway/`, implementations in `infrastructure/gateway/`
- DTOs in `infrastructure/dtos/`, mappers in `infrastructure/mapper/` (MapStruct interfaces only)
- Controllers in `infrastructure/presentation/`
- Beans (wiring) in `infrastructure/beans/`

**Database / Flyway**
- Migrations go in the correct module subdirectory (`userModel/user/`, `userModel/person/`, `userModel/information/`, `agenciesModel/`)
- Version numbers must follow the module's existing sequence (spaced by 10 to allow insertions)
- New `agenciesModel/` subdirectories must be added to `flyway.locations` in `application.yaml`
- `hibernate.ddl-auto` must remain `validate` — schema is never owned by Hibernate

**MapStruct**
- Mappers are interfaces annotated with `@Mapper`; no manual mapping logic inside them

**General code quality**
- No unnecessary abstractions or layers beyond what the task requires
- No comments explaining *what* the code does — only *why* (hidden constraints, non-obvious invariants)
- No unused imports, dead code, or backwards-compatibility shims

Report issues grouped by severity: **Critical** (breaks architecture contract), **Warning** (convention deviation), **Suggestion** (improvement opportunity). For each issue include the file path and line number.