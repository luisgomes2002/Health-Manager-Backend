Review the current state of the codebase and update CLAUDE.md to reflect any changes that are not yet documented. Do not rewrite sections that are still accurate.

Steps:
1. Read the current CLAUDE.md
2. Run `git diff main...HEAD --name-only` to see what files changed in this branch
3. For each changed area, check if CLAUDE.md already covers it accurately
4. Update only the sections that are stale or missing

Things to keep in mind:
- **New modules or features**: if a new domain module was added (new folder under a feature), document its layer structure
- **New Flyway migration paths**: if a new subdirectory was added under `db/migration/`, update the migration table and note if `flyway.locations` in `application.yaml` was updated
- **New environment variables**: document them under "Infrastructure dependencies"
- **New domain concepts**: entities, enums, roles, or invariants that a future developer needs to know
- **Removed or renamed things**: remove stale references

Do not:
- Add comments explaining *what* code does — CLAUDE.md documents *why* things are the way they are and *where* to find things
- Document patterns already obvious from the code structure
- Rewrite accurate sections

After updating, show a diff of what changed and explain each modification briefly.