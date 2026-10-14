# SGP — Sistema de Gestão de Pedidos (Signova Soluções Digitais)

API back-end para registrar e acompanhar vendas de certificado digital, substituindo o controle manual em planilha de uma operação real.

> **Status:** em desenvolvimento. A modelagem das entidades está pronta; repository, service e controller são a próxima etapa.

## Sobre o projeto

A Signova Soluções Digitais, fundada por mim em 2023, vende certificados digitais (e-CPF e e-CNPJ) em parceria com contabilidades. Hoje o controle das vendas é feito em planilha. O SGP nasce para registrar cada venda com integridade de dados: cliente, empresa (quando e-CNPJ), parceiro indicador, forma de pagamento, tipo de atendimento, protocolo e pedido da certificadora.

## Fluxo de cadastro (visão de negócio)

1. **Cliente:** nome completo, CPF, data de nascimento, e-mail e celular. Se o CPF já existe, o cliente é reaproveitado.
2. **Tipo de certificado:** e-CPF ou e-CNPJ. Se for e-CNPJ, cadastra a empresa (CNPJ e razão social) ligada ao cliente.
3. **Venda:** ponto de venda (Signova ou AKAT), parceiro, valor, forma de pagamento, tipo de atendimento, protocolo e pedido CCN. A venda nasce `PENDENTE` e depois vira `FINALIZADA` ou `CANCELADA`.

## Modelo de dados

| Entidade | Papel | Relacionamentos |
|---|---|---|
| `Cliente` | Pessoa que compra o certificado (CPF único) | — |
| `Empresa` | CNPJ e razão social (CNPJ único) | `@ManyToOne` para `Cliente` (responsável) |
| `Venda` | Registro central do pedido | `@ManyToOne` para `Cliente` e, opcionalmente, `Empresa` |

Decisões de modelagem:
- Composição em vez de herança: a empresa *tem* um cliente responsável, não *é* um cliente.
- `id` gerado pelo banco (`IDENTITY`) e CPF/CNPJ como campos `unique`, em vez de chave natural.
- Vocabulários fechados (ponto de venda, forma de pagamento, tipo de atendimento, status) como `enum` gravado como texto.
- Valores monetários em `BigDecimal`.
- Validação com Bean Validation (`@NotBlank`, `@Pattern`, `@Email`, `@NotNull`).
- Abordagem *code-first*: o Hibernate gera as tabelas a partir das entidades.

## Stack

- Java 21
- Spring Boot 4.1 (Web MVC, Data JPA, Validation, DevTools)
- Oracle Database 21c XE
- Maven

## Como rodar localmente

1. Crie um usuário/schema dedicado no Oracle (PDB `xepdb1`) com permissão de criar tabelas.
2. Defina a variável de ambiente `SGP_DB_PASSWORD` com a senha desse usuário.
3. Em `backend/sgp`, confira o `src/main/resources/application.properties` (URL `jdbc:oracle:thin:@localhost:1521/xepdb1` e usuário).
4. Rode a classe `SgpApplication`. O Hibernate cria as tabelas na primeira execução.

A senha nunca fica no repositório: o `application.properties` lê o valor da variável de ambiente.

## Estrutura do repositório

```
backend/
└── sgp/          # projeto Spring Boot
frontend/         # a ser criado
```

## Roadmap

- [x] Projeto Spring Boot conectado ao Oracle
- [x] Entidades JPA: `Cliente`, `Empresa` e `Venda`, com relacionamentos e validações
- [ ] Repositories (busca de cliente por CPF e de empresa por CNPJ)
- [ ] Services com as regras de cadastro
- [ ] Controllers REST (CRUD de vendas) com `@Valid`, testados no Postman
- [ ] Cadastro de parceiro como entidade própria
- [ ] Cálculo de fechamento por parceiro
- [ ] Front-end
- [ ] Chatbot de apoio ao preenchimento
- [ ] Testes automatizados

## Como foi desenvolvido — sobre o uso de IA

Uso o Claude (Anthropic) como mentor técnico: ele revisa o meu código, aponta erros de design, explica o porquê de cada correção e me devolve a tarefa para eu tentar de novo. As classes do projeto são escritas por mim; a IA entra na revisão e em explicações de conceito, com trechos de exemplo pontuais. Considero esse processo parte do aprendizado, porque aprendo tanto errando e sendo corrigido quanto acertando de primeira.

## Autor

**Kleber Lacerda** — em transição de carreira para Back-End Java, cursando Análise e Desenvolvimento de Sistemas na FIAP e fundador da Signova Soluções Digitais.
