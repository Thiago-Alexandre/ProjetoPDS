# ProjetoPDS

## Sistema de Controle Patrimonial

### Protótipo PDS I

O primeiro protótipo foi desenvolvido no 5º semestre, com cadastros básicos, controle de usuários e paginação. Neste período, o foco ficou para a análise do problema e construção da documentação do sistema. Utilizou-se JSF e JPA para a criação do projeto e o banco foi estruturado conforme a análise, não sendo gerado via ORM. O controle de usuários foi feito com base no *PhaseListener*.

### Protótipo PDS II

A continuação do protótipo será realizada no 6º semestre, mas utilizando novas tecnologias. O sistema contará com uma API Rest desenvolvida em Spring Web e Spring Data, onde a persistência será totalmente realizada com JPA, até a geração do banco de dados. O sistema contará também com uma aplicação JSF que consumirá a API e será a interação com os usuários. O protótipo de PDS I não será alterado, pois servirá de base para a implementação do protótipo de PDS II. A análise do sistema também sofrerá refatoração, pois será utilizado padrões de projeto no controle de materiais.

#### Etapa 1:

Desenvolvido a API Rest para gerenciar a estrutura da organização, sendo possível o cadastro, alteração e pesquisa de dados das Organizações que utilizarão o sistema, juntamente com suas Filiais, seus Setores e Funcionários. Administradores do sistema e Usuários das organizações também já estão implementados.

#### Etapa 2:

Adicionadas e mapeadas novas classes do sistema. Agora existe o gerenciamento dos materias, utilizando padrão Strategy para controlar cada tipo de material e suas formas de depreciação. Isso permite a flexibilidade do sistema, podendo ser adicionadas novas regras de depreciação e alterada a depreciação da cada material quando for necessário. Através do mapeamento ORM, o banco gerado utilizou a estratégia de criar a classe pai com todos os atributos das classes filhas, nas situações onde ocorre a herança. Tinha-se o interesse em utilizar o padrão Decorator para gerenciar as manutenções dos itens permanentes, mas no final não foi possível, por não haver total conhecimento de mapear classes e interfaces com ORM. Outro incremento foi o gerenciamento das entradas, onde poderia também ser utilizado o padrão Strategy, mas, neste caso, optou-se poder realizar sobrecarregamento de métodos, mesmo deixando o sistema "engessado".