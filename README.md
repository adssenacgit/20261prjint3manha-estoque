# Estoque API - Spring Boot Java 21

Projeto backend em Java 21 com Spring Boot, JPA, MySQL, Swagger/OpenAPI e CRUD completo para as tabelas:

- `usuario`
- `produto`
- `entrada`
- `entrada_produto`
- `venda`
- `venda_produto`

## Regras de status

O projeto usa exclusão lógica:

- `1` = ativo
- `0` = inativo
- `-1` = apagado logicamente

Os endpoints `GET` retornam apenas registros com status diferente de `-1`.
O endpoint `DELETE` não remove o registro fisicamente; apenas altera o status para `-1`.

## Tecnologias

- Java 21
- Spring Boot 3.5.8
- Spring Data JPA
- MySQL Connector/J 5.1.49
- SpringDoc OpenAPI / Swagger
- Maven

## Configuração do banco

Edite o arquivo:

```text
src/main/resources/application.properties
```

Troque:

```properties
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

O arquivo não está em YAML, conforme solicitado.

## Script do banco

O script está em:

```text
src/main/resources/database.sql
```

## Executar

```bash
mvn spring-boot:run
```

## Swagger

Depois de subir o projeto, acesse:

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints principais

### Usuários

- `GET /api/usuarios`
- `GET /api/usuarios/{id}`
- `POST /api/usuarios`
- `PUT /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`
- `PATCH /api/usuarios/{id}/ativar`
- `PATCH /api/usuarios/{id}/inativar`

### Produtos

- `GET /api/produtos`
- `GET /api/produtos/{id}`
- `POST /api/produtos`
- `PUT /api/produtos/{id}`
- `DELETE /api/produtos/{id}`
- `PATCH /api/produtos/{id}/ativar`
- `PATCH /api/produtos/{id}/inativar`

### Entradas

- `GET /api/entradas`
- `GET /api/entradas/{id}`
- `POST /api/entradas`
- `PUT /api/entradas/{id}`
- `DELETE /api/entradas/{id}`
- `PATCH /api/entradas/{id}/ativar`
- `PATCH /api/entradas/{id}/inativar`

### Itens de entrada

- `GET /api/entradas-produtos`
- `GET /api/entradas-produtos/{id}`
- `POST /api/entradas-produtos`
- `PUT /api/entradas-produtos/{id}`
- `DELETE /api/entradas-produtos/{id}`
- `PATCH /api/entradas-produtos/{id}/ativar`
- `PATCH /api/entradas-produtos/{id}/inativar`

### Vendas

- `GET /api/vendas`
- `GET /api/vendas/{id}`
- `POST /api/vendas`
- `PUT /api/vendas/{id}`
- `DELETE /api/vendas/{id}`
- `PATCH /api/vendas/{id}/ativar`
- `PATCH /api/vendas/{id}/inativar`

### Itens de venda

- `GET /api/vendas-produtos`
- `GET /api/vendas-produtos/{id}`
- `POST /api/vendas-produtos`
- `PUT /api/vendas-produtos/{id}`
- `DELETE /api/vendas-produtos/{id}`
- `PATCH /api/vendas-produtos/{id}/ativar`
- `PATCH /api/vendas-produtos/{id}/inativar`

## Exemplos de JSON

### Criar usuário

```json
{
  "usuarioNome": "João Silva",
  "empresaNome": "Empresa Teste",
  "usuarioEmail": "joao@email.com",
  "usuarioCpf": "123.456.789-00",
  "usuarioSenha": "123456",
  "usuarioStatus": 1
}
```

### Criar produto

```json
{
  "produtoNome": "Arroz 5kg",
  "produtoQuantidade": 10,
  "produtoPreco": 25.90,
  "produtoCodigo": 1001,
  "produtoDataValidade": "2026-12-31",
  "produtoStatus": 1
}
```

### Criar entrada

```json
{
  "entradaFornecedor": "Fornecedor A",
  "entradaValorTotal": 250.00,
  "entradaStatus": 1,
  "usuarioId": 1
}
```

### Criar item de entrada

```json
{
  "entradaId": 1,
  "produtoId": 1,
  "quantidade": 10,
  "precoCompra": 20.00,
  "entradaProdutoStatus": 1
}
```

### Criar venda

```json
{
  "vendaValorTotal": 59.80,
  "vendaStatus": 1,
  "usuarioId": 1
}
```

### Criar item de venda

```json
{
  "produtoId": 1,
  "vendaQuantidade": 2,
  "vendaPrecoUnidade": 29.90,
  "vendaProdutoStatus": 1,
  "vendaId": 1
}
```
