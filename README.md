                  Padrões de Projeto Utilizados

    Nos códigos fornecidos, são aplicados diversos padrões de projeto comumente utilizados em aplicações Java. Estes padrões visam promover a manutenção, teste e reutilização de código, além de facilitar a organização e estruturação das aplicações. A seguir, destacam-se os principais padrões presentes:

    Injeção de Dependência (Dependency Injection - DI)
    A injeção de dependência é utilizada através da anotação @Autowired, presente nos campos PedidoRepository e PedidoService no controlador PedidoController. Esta prática facilita a manutenção do código, promove a separação de responsabilidades e o desacoplamento entre as classes, tornando o código mais modular e flexível.

    Padrão MVC (Model-View-Controller)
    O padrão Modelo-Visão-Controlador (MVC) é aplicado na estrutura do controlador PedidoController. O controlador trata das requisições HTTP, delegando a lógica de negócio para o serviço PedidoService e retornando as respostas HTTP adequadas. O modelo (Pedido e PedidoRepository) representa os dados, enquanto a visualização não está explicitamente presente neste código. O controlador é responsável por orquestrar a interação entre a visualização e o modelo.

    Padrão Repository
    O padrão Repository é representado pela classe PedidoRepository. Nela, são definidas as operações de persistência de dados relacionadas aos pedidos. Estas operações são abstraídas para permitir consultas e modificações no banco de dados, seguindo o princípio de separação de responsabilidades.

    Padrão DTO (Data Transfer Object)
    Embora não seja explicitamente mencionado nos códigos fornecidos, é comum utilizar DTOs para transferir dados entre as camadas da aplicação. O objeto Pedido poderia ser considerado um DTO, representando os dados do pedido que são transferidos entre o cliente e o servidor através das requisições HTTP.

    Padrão Factory (potencialmente)
    Embora não seja explicitamente implementado nos códigos fornecidos, o padrão Factory pode ser aplicado para criar instâncias dos objetos necessários, como os serviços (PedidoService) ou repositórios (PedidoRepository).

