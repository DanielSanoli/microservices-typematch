# microservices-typematch

Microserviço responsável por comparar tipos e vantagens/desvantagens entre dois Pokémon, consumindo dados do `microservices-gym-leader` e/ou PokeAPI.

## Visão geral

Expõe uma API versionada em `/api/v1/compare/**` e é roteado pelo `porygonz-gateway`.

## Stack
- Java + Spring Boot
- Consumo HTTP via `RestTemplate`

## Portas
- **8082** (padrão)

## Endpoints
- `GET /api/v1/compare/{pokemonA}/{pokemonB}`

> `pokemonA` e `pokemonB` podem ser ID ou nome (conforme sua implementação).

## Configuração (Environment Variables)

| Variável | Obrigatória | Exemplo | Descrição |
|---|---:|---|---|
| `POKEDEX_BASE_URL` | sim (em docker) | `http://gym-leader:8081/api/v1/pokemon` | Base URL do Gym Leader para buscar Pokémon |

No `application.yml/properties`, isso normalmente mapeia para:
- `pokedex.base-url`

## Rodando local (sem Docker)
Requisitos:
- Java 21
- `microservices-gym-leader` rodando (ou ajustar `pokedex.base-url` para o endpoint correto)

```bash
./mvnw spring-boot:run
```

## Rodando com Docker (via microservices-infra)
Recomendado subir via `microservices-infra`.

## Roadmap
- Propagação de Correlation-Id em chamadas downstream (se ainda não estiver completo)
- Timeouts e retries no RestTemplate
- Melhor padronização de erros