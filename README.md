# Sobre o Projeto

Projeto desenvolvido para seleção do IFCE

## Tecnologias utilizadas

Este projeto utiliza as seguintes tecnologias:

- Sprint Boot

- Java 11

- Hibernate

- JPA

- Sprint Data

- Junity

- Spring Security

- Lombok

- PostgresSQL


## Configurações para manipular o projeto com o Lombok

Com o objetivo de melhorar a legibilidade do codigo, optei por usar o lombok, para facilitar a criação de métodos padrões  de classes por meio de anotações.

Para configurar o lombok e manipular o código usando o eclipse, siga as orientações do link: https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/

## Configurações do banco de dados

A Aplicação está preparada para rodar com uma base de dados do tipo PostgresSQL e gerar as tabelas e colunas automaticamente, no momento em que a mesma é iniciada. 

Porém, antes de iniciar a aplicação o usuário deve criar os bancos de dados:

- selecao_ifce
- selecao_ifce_test (utilizada para rodar os testes unitários )



## Gerando um Token para acesso aos recursos da API com o Postman.

Devido ao requisito de segurança, implementei uma camada de segurança utilizando o Sprint Security. Com isso, todas as chamadas  da api desenvolvida, necessida de um Token.

Para a geração to Token, o usuário necessita fazer uma chamada HTTP do tipo POST, na seguinte URL: localhost:8080/selecao-api/oauth/token.

O usuário deve passar as seguintes credenciais de autentificarão via Basic Auth: 

- Username: fooClientId

- Password: secret

O Usuário deve também passar no body da requisição as seguintes informações to tipo, x-www-form-urlencoded:

- grant_type: password

- username: ifce

- password: 123456

Apos fazer essas configuraçoes e mandar executar o POST, a api deve retornar um JSON de resposta, contendo um campo denominado access_token. O Usuário deve então pegar o valor desse campo e colocar na autorização do tipo Token, das URL que deseja ter acesso da API.
