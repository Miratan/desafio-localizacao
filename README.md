# desafio-localizacao

Aplicação responsável por mostrar os representantes e as lojas próximas de acordo com a localização de ambos.

## Instalar e Rodar

```
TODO
```

## Decisões Tecnológicas

```
* Java 1.8;
* Spring Boot 2.1.3.RELEASE;
* H2 database;
* React;
* Redux;
```

## Funcionalidades

```
* Leitura de arquivo CSV;
* Armazenamento dos dados no banco H2;
* Serviço para listar representantes;
* Serviço para listar lojas;
* Serviço para listar representantes e lojas próximas (máximo de 2km);
* Criação do projeto frontend;
* Integrar com maps, mostrando representante e lojas próximas;
* Representante com menor quantidade de lojas é selecionado quando loja pode visitada por mais de um.
```

## Backlog

```
* Mudar comparação para verificar por menor número de lojas visitadas e depois por proximidade. Atualmente verifica o menor número de lojas e pega por ordem alfabética.
* Adicionar filtro para modo de ordenação (Pŕoximo / Distribuído);
* Permitir alteração da configuração de proximidade (não deixar fixo 2km);
* Serviço para listar representantes paginados;
* Serviço para listar lojas paginadas;
* Adição, edição e exclusão de representantes;
* Adição, edição e exclusão de lojas;
* Criação de login com perfis diferenciados para o dono da empresa e representantes;
```