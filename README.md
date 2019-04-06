# desafio-localizacao

Aplicação responsável por mostrar os representantes e as lojas próximas de acordo com a localização de ambos.

## Instalar e Rodar

[ x ] Baixar o projeto

```bash
git clone https://github.com/Miratan/desafio-localizacao
```

### Backend

[ x ] Ir para a pasta do projeto backend

```bash
cd desafio-localizacao-server
```

[ x ] Compilar e subir o backend

```bash
mvn clean -DskipTests & mvn spring-boot:run
```

[ x ] Para rodar os testes executar

```bash
mvn test
```

### Frontend

[ x ] Ir para a pasta do projeto frontend

```bash
cd desafio-localizacao-app
```

[ x ] Instalar dependências e rodar o frontend

```bash
npm install && npm start
```

[ x ] Rodar os testes

```bash
npm test
```

## Decisões Tecnológicas

```
* Java 1.8;
* Spring Boot 2.1.3.RELEASE;
* H2 database;
* React;
* Redux;
```

- A carga inicial das informações de funcionários e lojas é realizada no momento em que a aplicação sobe.

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
* Adicionar filtro para modo de ordenação (Pŕoximo / Distribuído);
* Mudar comparação para verificar por menor número de lojas visitadas e depois por proximidade. Atualmente verifica o menor número de lojas e pega por ordem alfabética.
* Serviço para listar lojas paginadas;
* Serviço para listar representantes paginados;
```

## Backlog

```
* Permitir alteração da configuração de proximidade (não deixar fixo 2km);
* Adição, edição e exclusão de representantes;
* Adição, edição e exclusão de lojas;
* Criação de login com perfis diferenciados para o dono da empresa e representantes;
```
