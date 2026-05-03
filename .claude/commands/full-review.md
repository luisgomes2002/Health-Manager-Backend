Run all three review commands in parallel on the current branch changes and consolidate the results.

1. **Quality review** — check architectural patterns, naming conventions, Flyway rules, MapStruct usage (same rules as /quality-review)
2. **Security review** — check JWT, SQL injection, PII handling, secrets exposure (same rules as /sec-review)
3. **CLAUDE.md review** — check if any change requires updating the documentation (same rules as /update-claude-md)

Present the final report in three clearly separated sections:

---
## Quality
<findings>

---
## Security
<findings>

---
## CLAUDE.md
<findings or "No updates needed">
---

For quality and security sections, group findings by severity: **Critical**, **Warning**, **Suggestion**. Include file path and line number for each finding. If a section has no findings, write "No issues found."