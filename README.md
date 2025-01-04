# Controller Task

Este projeto é um sistema de gerenciamento de tarefas e perfil de usuário, desenvolvido utilizando o framework Spring Boot. Ele permite aos usuários se registrarem, gerenciarem seu perfil e tarefas, com funcionalidades como criação, edição, visualização e remoção de tarefas. O sistema também envia lembretes para as tarefas agendadas.

## Funcionalidades

### 1. **Gerenciamento de Tarefas**
   - **Criação de tarefas**: Os usuários podem criar tarefas com nome, descrição, data de vencimento e status.
   - **Edição de tarefas**: As tarefas podem ser editadas a qualquer momento.
   - **Exclusão de tarefas**: Usuários podem excluir tarefas.
   - **Visualização de detalhes**: Exibição detalhada das tarefas com a possibilidade de visualizar a imagem associada.
   - **Concluir tarefa**: O status da tarefa pode ser alterado para "concluído".
   - **Agendamento de lembretes**: Envio de lembretes por e-mail quando a tarefa estiver próxima de sua data de vencimento.

### 2. **Gerenciamento de Perfil de Usuário**
   - **Cadastro de usuário**: Usuários podem se registrar fornecendo nome de usuário e senha.
   - **Login de usuário**: Acesso ao sistema com nome de usuário e senha.
   - **Edição de perfil**: Usuários podem atualizar seu perfil, incluindo avatar, imagem principal, nickname e frase.

## Tecnologias Utilizadas
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **Thymeleaf**: Template engine para renderização das páginas HTML.
- **Java**: Linguagem de programação utilizada no projeto.
- **HTML, CSS**: Para estruturação e estilização das páginas.
- **JavaScript**: Para interação dinâmica no frontend.
- **MySQL**: Banco de dados utilizado para armazenar usuários, perfis e tarefas.
