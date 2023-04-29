
# MechanicOps

MechanicOps é um sistema de gerenciamento de oficinas simples e eficiente, construído com Java, que oferece uma experiência aprimorada para o usuário. Ele permite que os usuários gerenciem orçamentos, vendam produtos e verifiquem informações de produtos através de um menu de linha de comando intuitivo.
MechanicOps foi projetado com foco na simplicidade e na usabilidade, tornando-o uma ferramenta indispensável para oficinas de todos os portes.


## Funcionalidades

- Gerenciamento de orçamentos.
- Sistema de gerenciamento de estoque.
- Vendas de produto.
- Envio de email quando uma venda for concretizada.
- Logs, saiba o que foi vendido, quem vendeu e quando vendeu.
- Sistema de aviso quando produtos estiverem em poucas quantidades.


## Demonstração

Em breve!


## Rodando localmente

- Certifique-se de ter o Java instalado em seu computador. Caso não tenha, faça o download e instalação em https://www.java.com/pt-BR/download/.

Clone ou faça o download deste repositório em seu computador.
```bash
  git clone git@github.com:kevinfinalboss/MechanicOps.git
```

Entre no diretório do projeto

```bash
  cd MechanicOps
```

Compile o código digitando o seguinte comando no terminal:

```bash
  javac -cp src/main/java -d target/classes src/main/java/mechanicops/view/Menu.java
```

Execute a aplicação com o seguinte comando:

```bash
  java -cp target/classes mechanicops.view.Menu
```


## FAQ

#### Como é feita a interação com o usuário na aplicação?

A interação com o usuário é feita principalmente através da classe Menu. Essa classe exibe um menu de opções ao usuário e aguarda a entrada do usuário para executar a ação correspondente. As entradas do usuário são lidas através do objeto Scanner. Algumas outras classes, como VerificadorProdutos, também interagem com o usuário em certas partes da aplicação.

#### Qual é a arquitetura utilizada na aplicação MechanicOps?

A aplicação MechanicOps utiliza uma arquitetura de camadas, com uma camada de apresentação, uma camada de controle e uma camada de modelo. A camada de apresentação é responsável por interagir com o usuário, a camada de controle gerencia a lógica da aplicação e a camada de modelo representa os dados e as regras de negócio. Essa arquitetura ajuda a manter a aplicação organizada e modular, permitindo que as alterações em uma camada não afetem as outras camadas.


## Autores

- [@kevinfinalboss](https://www.github.com/kevinfinalboss)
- [@VitorJimenez](https://www.github.com/kevinfinalboss)


## Documentação
Aqui está a documentação de algumas classes, métodos e atributos do projeto, lembrando que o projeto ainda está na fase de desenvolvimento.

- Classe GerenciadorOrcamentos:
  A classe GerenciadorOrcamentos é responsável por gerenciar os dados dos orçamentos da aplicação. Ela contém os seguintes métodos:
- public int proximoId(): Retorna o próximo id disponível para um novo orçamento.
- public List<Orcamento> listarOrcamentos(): Retorna uma lista com todos os orçamentos cadastrados.
- public void adicionarOrcamento(Orcamento orcamento): Adiciona um novo orçamento à lista de    orçamentos.
- private void salvarOrcamentos(List<Orcamento> orcamentos): Salva a lista de orçamentos no arquivo orcamentos.json.

Classe GerenciadorProdutos:

A classe GerenciadorProdutos é responsável por gerenciar os dados dos produtos da aplicação. Ela contém os seguintes métodos:
- public int proximoId(List<Produto> produtos): Retorna o próximo id disponível para um novo produto, levando em consideração a lista de produtos já cadastrados.
- public List<Produto> listarProdutos(): Retorna uma lista com todos os produtos cadastrados.
- public void adicionarProduto(Produto produto): Adiciona um novo produto à lista de produtos.
- private void salvarProdutos(List<Produto> produtos): Salva a lista de produtos no arquivo produtos.json.

Classe Orcamento:
A classe Orcamento representa um orçamento da aplicação. Ela contém os seguintes atributos:

- private int id: Id do orçamento.
- private String nomeCliente: Nome do cliente.
- private String telefoneCliente: Telefone do cliente.
- private String data: Data em que o orçamento foi criado.
- private String carroModeloAno: Informações sobre o carro do cliente.
- private float valorConserto: Valor do conserto.
- private String situacao: Situação do orçamento (aberto, fechado, etc)




## Suporte

Para suporte, mande um email para kevin.gomes@kevindev.com.br ou abra uma issue nesse repositório.


## Feedback

Se você tiver algum feedback, por favor nos deixe saber por meio de kevin.gomes@kevindev.com.br


## Licença

[MIT](https://choosealicense.com/licenses/mit/)

