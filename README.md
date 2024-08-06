# Desafio Banco Java Backend

## Descrição

O projeto **Desafio Banco Java Backend** tem como objetivo simular um sistema bancário completo. Abaixo está um resumo das funcionalidades:

- **Cadastro de Pessoas:** Utilize a função AddNewPessoa na classe PessoaController para cadastrar novas pessoas. Esta função comunica-se com a classe PessoaService, que, por sua vez, salva o novo objeto Pessoa no PessoaRepository. Cada pessoa cadastrada recebe automaticamente um score aleatório de 0 a 9.
  
- **Criação de Contas:** Para cada pessoa cadastrada, é possível criar uma conta. Ao informar a agência, a conta é criada automaticamente, incluindo limites de cheque especial e cartão de crédito baseados no score da pessoa.

## Como Rodar

Para iniciar o sistema, siga estas etapas:

1. Navegue até a pasta src/main/java/com/example/demo.
2. Execute a classe DemoApplication. Esta classe inicializa o sistema e o coloca em funcionamento.

### Cadastro de Pessoas

- **Endpoint:** localhost:8080/api/pessoas
- **Método:** POST
- **Body:** Objeto JSON representando uma pessoa(nome e documento).

O sistema já cria automaticamente 3 exemplos de pessoas ao iniciar.

## Dockerfile

O Dockerfile disponível no projeto contém um banco de dados PostgreSQL que armazena os dados das pessoas e suas contas cadastradas no sistema.

### Construir e Rodar o Container

Para construir e rodar o container Docker, utilize os seguintes comandos:

```bash
docker build -t desafio-banco-java-backend .
docker run -p 5432:5432 desafio-banco-java-backend
