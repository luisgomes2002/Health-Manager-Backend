Perform a security review of all staged and unstaged changes in this branch. Focus on the project's specific stack: Spring Boot, JWT (JJWT 0.12.6), PostgreSQL, Flyway, and MapStruct.

**Authentication & Authorization**
- JWT secret is read from `${SECURITY_SECRET}` env var — verify it is never hardcoded or logged
- `SecurityFilter` must always validate the Bearer token before setting the principal; check for bypass paths
- Protected routes: only `/api/v1/users/create` and `/api/v1/auth/login` should be unauthenticated — any new public endpoint must be intentional and documented
- `TokenData` fields (`userId`, `email`, `role`, `fullname`) must not be trusted from request input; they must come from the verified token only

**Input validation & injection**
- All user-supplied input entering the domain must be validated at the controller/DTO boundary (OWASP top 10)
- No string concatenation in JPQL or native queries — use named/positional parameters only
- Flyway SQL migrations must not include dynamic/user-supplied values

**Sensitive data handling**
- CPF, CNPJ, and other PII fields: check if encryption/masking is applied consistently (see `EncryptedStringConverter`)
- Passwords and secrets must never appear in logs, error responses, or serialized DTOs
- `ErrorResponse` must not leak stack traces or internal implementation details to the client

**Dependency & configuration**
- No credentials or secrets committed to source (`.yaml`, `.properties`, `.env` files)
- Docker Compose default passwords (`teste`) are acceptable for local dev only — flag if they appear in non-dev config

Report issues grouped by severity: **Critical** (exploitable vulnerability), **Warning** (security smell or misconfiguration), **Info** (hardening suggestion). Include file path and line number for each finding.