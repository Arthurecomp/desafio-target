# Desafio Técnico - API em Spring Boot

API REST com três funcionalidades: 
1 - cálculo de comissão de vendedores, 
2 - movimentação de estoque e 
3 - cálculo de juros por atraso.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- H2 (banco em memória)
- Maven


Ao iniciar, os arquivos `vendas.json` e `estoque.json` (em `src/main/resources`) são lidos com Jackson e gravados no H2 por um `CommandLineRunner` (`DataLoader`).

## Estrutura

src/main/java/com/target/desafio/
├── config/ # DataLoader (carga dos JSONs)
├── controller/ # Endpoints REST
├── dto/ # Objetos de entrada/saída
├── entity/ # Entidades JPA
├── repository/ # Repositórios
└── service/ # Regras de negócio


## Endpoints
---
Os 3 endpoints são as 3 respostas do desafio
---

### 1. Comissões
`GET /vendas/comissoes`

Calcula a comissão total de cada vendedor, venda a venda:

Vendas abaixo de R$100,00 não gera comissão
Vendas abaixo de R$500,00 gera 1% de comissão
A partir de R$500,00 gera 5% de comissão

Resposta: valor total de comissão por vendedor.

### 2. Movimentação de estoque

`POST /estoque/movimentacoes`

```json
{
  "codigoProduto": 101,
  "tipo": "SAIDA",
  "descricao": "Venda",
  "quantidade": 20
}
```

- `tipo`: `ENTRADA` ou `SAIDA`
- Cada movimentação recebe um identificador único.
- A descrição identifica o tipo da movimentação.
- A resposta retorna a quantidade final em estoque do produto movimentado.

Exemplo: Caneta Azul (101) começa com 150. Após uma saída de 20, o retorno é `130`.

### 3. Cálculo de juros

`POST /juros/calcular`

```json
{
  "valor": 1000.00,
  "dataVencimento": "2026-09-30"
}
```

Os juros são calculados sobre os dias de atraso até a data de hoje, à taxa de 2,5% ao dia:

juros = valor × 0,025 × dias de atraso


Exemplo: R$ 1.000,00 com 3 dias de atraso resulta em R$ 75,00.
Se a data de vencimento não estiver vencida, os juros são zero.

## Decisões e premissas

- O H2 em memória foi escolhido pela simplicidade; os dados são recarregados a cada inicialização.
- Os JSONs são lidos na inicialização para manter a origem dos dados conforme o enunciado.
- Saídas de estoque maiores que o saldo disponível são rejeitadas.
- Valores monetários usam `BigDecimal` para evitar erros de arredondamento.
