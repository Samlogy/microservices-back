# Monolithic Architecture

## Tech Stack
### Language & Framework
- Java 20, Maven 3.13
- Spring 3+
- Postgresql (db + replica => DB for each service)

### DevOps
- Jenkins (Pipeline)
- Terraform (infra)
- AWS S3 (save static content), EC2, ECR, ECS, EKS, RDS (postgres), DynamoDB/MongoDB (nosql)
- Kubernetes + Helm (orchestration, packaging)
- API gateway (TLS, Load balancer, caching)
- Keycloak (IAM)
- Vault: management & centralization of secrets

### Tools
- Git/Github (versioning, Env separation)
- SonarQube (code quality)

### Testing
- Gatling: (Performence/Scaling)
  Back: Unit/Integration
- Cypress: E2E