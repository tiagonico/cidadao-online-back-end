# Back End da aplicação Cidadão Online

Back-end da aplicação Cidadão Online, desenvolvido para o projeto de graduação do curso de Engenharia de Computação na UFES.

## 🛠️ Instalação

1️⃣ Primeiramente faça o download do projeto, seja baixando o arquivo .zip pelo navegador ou através do comando <i>git clone</i>.

2️⃣ Instale em seu sistema o <a href="https://www.java.com/pt-BR/">Java</a> e <a href="https://maven.apache.org/">Maven</a>, caso não tenha instalado.

3️⃣ No arquivo src/main/resources/application.properties complete as informações pedidas sobre a conta na AWS e bucket. 

<a href="https://docs.aws.amazon.com/pt_br/AmazonS3/latest/userguide/creating-bucket.html">Criar bucket no s3</a> e <a href="https://docs.aws.amazon.com/pt_br/IAM/latest/UserGuide/id_credentials_access-keys.html">Pegar chave de acesso</a>.

4️⃣ Na pasta raiz do projeto digite o comando no terminal para rodar a aplicação:
 
 ```
 mvn spring-boot:run
 ```
 
Esse projeto utiliza o banco de dados H2 no modo de teste, para acessar seu painel de controle basta 
inserir o endereço abaixo no seu navegador e clicar em "Connect".

```
localhost:8080/h2-console
```

Para testar os endpoints sugeridos no desafio pode ser utilizado o <a href="https://www.postman.com/">Postman</a>

⚠️ <b>ATENÇÃO:</b>

Rode o <a href="https://github.com/tiagonico/cidadao-online-front-end">Front-End</a> para usar a aplicação.
