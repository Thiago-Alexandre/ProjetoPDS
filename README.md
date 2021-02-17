# ProjetoPDS

## Sistema de Controle Patrimonial

### Protótipo PDS I

O primeiro protótipo foi desenvolvido no 5º semestre, com cadastros básicos, controle de usuários e paginação. Neste período, o foco ficou para a análise do problema e construção da documentação do sistema. Utilizou-se JSF e JPA para a criação do projeto e o banco foi estruturado conforme a análise, não sendo gerado via ORM. O controle de usuários foi feito com base no *PhaseListener*.

### Protótipo PDS II

A continuação do protótipo será realizada no 6º semestre, mas utilizando novas tecnologias. O sistema contará com uma API Rest desenvolvida em Spring Web e Spring Data, onde a persistência será totalmente realizada com JPA, até a geração do banco de dados. O sistema contará também com uma aplicação JSF que consumirá a API e será a interação com os usuários. O protótipo de PDS I não será alterado, pois servirá de base para a implementação do protótipo de PDS II. A análise do sistema também sofrerá refatoração, pois será utilizado padrões de projeto no controle de materiais.

#### Etapa 1:

Desenvolvido a API Rest para gerenciar a estrutura da organização, sendo possível o cadastro, alteração e pesquisa de dados das Organizações que utilizarão o sistema, juntamente com suas Filiais, seus Setores e Funcionários. Administradores do sistema e Usuários das organizações também já estão implementados.