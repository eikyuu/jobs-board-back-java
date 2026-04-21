# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./mvnw spring-boot:run       # Start the application
./mvnw test                  # Run all tests
./mvnw -Dtest=MyTest test    # Run a single test class
./mvnw clean package         # Build JAR
```

## Architecture

Spring Boot 4.0.5 REST API backend for a jobs board application.

- **Language:** Java 21
- **Web layer:** Jersey (JAX-RS) + Spring Web MVC
- **Database:** MySQL (driver included; Spring Data JPA not yet added — datasource not yet configured in `application.properties`)
- **Boilerplate reduction:** Lombok (excluded from final package)
- **Base package:** `dev.duguetvincent.jobsboardback`

The project is in early scaffolding stage: only the main `SpringApplication` entry point and a smoke test exist. Controllers, services, and repositories are yet to be built.
