# energia-service

API para acompanhar **geração e consumo de energia solar** — leituras, contas e séries históricas.

## Stack

| Item | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.1 |
| Spring Cloud | 2025.1.3 |

| Porta | Context path | Perfil exigido |
|---|---|---|
| 9001 | `/energia-service/` | `ROLE_ENERGIA` |

## Endpoints

### `/acompanhamentos` — leituras, contas e séries

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/acompanhamentos` | lista os acompanhamentos |
| `POST` | `/acompanhamentos` | registra um acompanhamento |
| `GET` | `/acompanhamentos/{id}` | busca por id |
| `PUT` | `/acompanhamentos/{id}` | atualiza |
| `DELETE` | `/acompanhamentos/{id}` | remove |
| `GET` | `/acompanhamentos/consumoultimos12meses` | consumo dos últimos 12 meses |
| `GET` | `/acompanhamentos/consumo12mesesanteriores` | consumo dos 12 meses anteriores |
| `GET` | `/acompanhamentos/saldoultimos12meses` | saldo acumulado |
| `GET` | `/acompanhamentos/geracaoultimos30dias` | geração dos últimos 30 dias |
| `GET` | `/acompanhamentos/geracaoultimos12meses` | geração dos últimos 12 meses |
| `GET` | `/acompanhamentos/geracao12mesesanteriores` | geração dos 12 meses anteriores |
| `GET` | `/acompanhamentos/valorultimos12meses` | valor pago nos últimos 12 meses |
| `GET` | `/acompanhamentos/contaultimomes` | conta do último mês |
| `GET` | `/acompanhamentos/geradosdesdeultimaleitura` | gerado desde a última leitura |

### `/geracao` — séries de geração

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/geracao/ultimos30dias` | últimos 30 dias |
| `GET` | `/geracao/ultimos30Diasanopassado` | mesmos 30 dias do ano anterior |
| `GET` | `/geracao/ultimos12meses` | últimos 12 meses |
| `GET` | `/geracao/ultimos13a24meses` | meses 13 a 24 |

### `/total`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/total` | totais consolidados |

## Autenticação

As requisições precisam do token JWT emitido pelo `autenticacao-service`, no cabeçalho:

```
Authorization: Bearer <token>
```

O serviço apenas **valida** o token — ele não emite nenhum. A chave de validação vem de `JWTSecret`, em `secret/application` no Vault, e precisa ser a mesma usada pelo emissor.

> **Atenção ao segredo:** desde a migração para o jjwt 0.13, `JWTSecret` precisa ser uma string **Base64** que decodifique para **no mínimo 64 bytes** — exigência do HS512. Gere um com `openssl rand -base64 64`. Se o valor não atender, a aplicação falha no startup com mensagem explícita, em vez de aceitar uma chave fraca em silêncio.

## Banco de dados

PostgreSQL, com schema versionado por **Flyway** (migrations em `src/main/resources/db/migration`):

- `V001__inicial.sql`
- `V002__add_column_acompanhamentos.sql`
- `V003__add_calculo_de_conta_sem_energia_solar.sql`
- `V004__update_tabela_totais.sql`
- `V005__add_column_acompanhamentos.sql`
- `V006__update_table_acompanhamentos.sql`
- `V007__add_column_acompanhamentos.sql`
- `V008__create_table_geracao.sql`
- `V009__create_table_processamentos.sql`

As migrations rodam automaticamente no startup.

> No Spring Boot 4 a autoconfiguração do Flyway passou a viver no módulo `spring-boot-flyway`. Sem essa dependência o Flyway é ignorado **em silêncio** — a aplicação sobe normalmente e nenhuma migration é aplicada. Ela está declarada no `pom.xml`; não remova.

**Entidades:** `Acompanhamento`, `Geracao`, `Processamento`, `Total`.

## Testes

Este é o único serviço do conjunto com suíte de testes própria — testes de repositório (`@DataJpaTest`), de controller (`@WebMvcTest`) e de integração (`@SpringBootTest`).

```bash
./mvnw test
```

> Os testes de integração precisam do **Config Server no ar**; sem ele o contexto não sobe e a suíte falha em bloco.

## Configuração

A aplicação não guarda configuração própria: ela busca tudo no arranque, via `spring.config.import`.

| Origem | O que vem de lá |
|---|---|
| **Vault** (`secret/application`) | segredos compartilhados: `JWTSecret`, credenciais de e-mail, AWS, Eureka |
| **Vault** (`secret/<nome-do-serviço>`) | segredos próprios, como as credenciais do banco |
| **Config Server** | `server.port`, `context-path`, datasource e demais propriedades |

### Variável de ambiente obrigatória

| Variável | Para que serve |
|---|---|
| `VAULT_TOKEN` | token de acesso ao Vault |

`VAULT_TOKEN` **não tem valor padrão**. Sem ela, o Spring envia a string literal `${VAULT_TOKEN}` ao Vault, recebe `403` e — como `spring.cloud.vault.fail-fast` vem desligado — o erro só aparece bem depois, disfarçado de placeholder não resolvido (`${...} is malformed`). Se quiser que a falha apareça na hora, ligue `spring.cloud.vault.fail-fast: true`.

Também são necessários `VAULT_HOST`, `VAULT_PORT` e `VAULT_SCHEME` quando o Vault não está em `localhost:8200` via `http`, e `CONFIG_SERVER_USER` / `CONFIG_SERVER_PASS` nos serviços que leem do Config Server.

## Como executar

```bash
# build
./mvnw clean package

# execução
VAULT_TOKEN=<seu-token> java -jar target/energia-service-*.jar --spring.profiles.active=dev
```

> **Dependências no ar:** este serviço só sobe com o **Vault**, o **Config Server** e o **Eureka** disponíveis, além do seu banco PostgreSQL.

A aplicação sobe em `http://localhost:9001/energia-service/`.

### Docker

O `Dockerfile` espera o jar já na raiz do projeto, com o nome `sistema-energia-service.jar`:

```bash
./mvnw clean package
cp target/energia-service-*.jar sistema-energia-service.jar

docker build \
  --build-arg VAULT_HOST=<host> \
  --build-arg VAULT_TOKEN=<token> \
  --build-arg CONFIG_SERVER_USER=<usuario> \
  --build-arg CONFIG_SERVER_PASS=<senha> \
  -t energia-service .
```
