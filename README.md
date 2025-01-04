# Descrição do Projeto: **ControllerTask**

**ControllerTask** é uma aplicação web construída utilizando o framework **Spring Boot**, projetada para gerenciar usuários, perfis e tarefas de forma eficiente. O sistema permite a criação, edição e visualização de tarefas, além de oferecer funcionalidades de gestão de perfil de usuário, incluindo upload de imagens, e agendamento de lembretes de tarefas.

## Funcionalidades Principais

### 1. **Cadastro e Autenticação de Usuário**
- O sistema permite que novos usuários se cadastrem, criando um perfil. O processo de login é realizado de forma segura através de autenticação baseada em Spring Security.
    - **Cadastro de Usuário**: O usuário pode se cadastrar inserindo informações como nome, e-mail e senha.
    - **Login**: O usuário autentica-se utilizando suas credenciais e acessa a interface de tarefas.

### 2. **Gestão de Tarefas**
- O sistema permite que os usuários criem, editem e visualizem tarefas.
    - **Criação de Tarefas**: O usuário pode criar tarefas e associar uma imagem a elas.
    - **Exibição de Tarefas**: Todas as tarefas do usuário são exibidas na tela inicial.
    - **Edição de Tarefas**: O usuário pode editar os detalhes das tarefas existentes, incluindo nome, descrição, data de conclusão e status.
    - **Exclusão de Tarefas**: O usuário pode excluir tarefas.
    - **Lembretes de Tarefas**: O sistema permite que o usuário agende lembretes para tarefas específicas.

### 3. **Gestão de Perfil de Usuário**
- O usuário pode editar seu perfil, incluindo nome, avatar (imagem de perfil) e uma frase personalizada.
    - **Exibição do Perfil**: O usuário pode visualizar informações detalhadas sobre o seu perfil.
    - **Edição do Perfil**: O usuário pode atualizar seu nome, frase e imagem de perfil.

### 4. **Upload de Imagens**
- O sistema permite que os usuários façam upload de imagens de perfil e imagens relacionadas às tarefas, as quais são exibidas como base64.

### 5. **Tela Inicial e Navegação**
- A tela inicial apresenta uma lista de tarefas do usuário e informações relacionadas ao seu perfil, além de permitir fácil navegação para outras funcionalidades, como a criação e edição de tarefas.

---

## Estrutura do Projeto

A aplicação segue a arquitetura **MVC (Model-View-Controller)** e utiliza as seguintes tecnologias:

- **Spring Boot**: Framework principal para o desenvolvimento da aplicação.
- **Spring Security**: Para gerenciar a autenticação e autorização de usuários.
- **Spring Data JPA**: Para interação com o banco de dados e operações CRUD.
- **Thymeleaf**: Motor de templates para renderização de páginas HTML.
- **Base64**: Para codificar e exibir imagens (como avatares e imagens de tarefas).

---

## Estrutura de Pastas

- **Controller**: Contém os controllers responsáveis por lidar com as requisições HTTP e a lógica de negócios.
- **Model**: Contém as classes que representam os dados do sistema, como `User`, `Profile`, `Task`, entre outros.
- **Repository**: Responsável pela persistência de dados utilizando o Spring Data JPA.
- **View (Templates)**: Contém as páginas HTML renderizadas com Thymeleaf.

---

## Tecnologias Utilizadas

- **Spring Boot**: Framework para simplificar o desenvolvimento de aplicações Java.
- **Spring Security**: Para segurança da aplicação, incluindo autenticação de usuários.
- **Spring Data JPA**: Para fácil acesso a dados no banco de dados relacional.
- **Thymeleaf**: Para renderizar a interface do usuário com HTML dinâmico.
- **Base64**: Para representar imagens binárias em formato textual, como avatares de perfil e imagens de tarefas.

---

## Como Executar o Projeto

### 1. **Clonar o Repositório**:
   - Execute o comando: 
     ```bash
     git clone https://github.com/seu-repositorio/controllerTask.git
     ```

### 2. **Configurar o Banco de Dados**:
   - O projeto usa um banco de dados relacional. Configure a conexão do banco de dados no arquivo `application.properties`.

### 3. **Rodar o Projeto**:
   - Com o ambiente configurado, execute a aplicação:
     ```bash
     ./mvnw spring-boot:run
     ```
   - A aplicação estará disponível em `http://localhost:8080`.

### 4. **Acessar a Aplicação**:
   - Acesse a aplicação pelo navegador e utilize a interface para cadastrar usuários, criar tarefas e interagir com os dados.

---

### Imagens do Projeto

Aqui estão algumas imagens da interface do projeto para ilustrar o uso e o design das telas.

<div style="width:100%; overflow:hidden; max-width:600px;">
  <div style="display: flex; transition: transform 0.5s ease;">
    <img src="https://i.postimg.cc/Njqnh53P/1.png" alt="Imagem 1" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/4Nh0FzS9/2.png" alt="Imagem 2" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/6prmBBw6/3.png" alt="Imagem 3" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/Px370KHm/4.png" alt="Imagem 4" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/Gt65m2Ht/5.png" alt="Imagem 5" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/4dNM8qDf/6.png" alt="Imagem 6" style="width:100%; flex-shrink: 0; border: 5px solid black;">
    <img src="https://i.postimg.cc/gjx7pRc1/7.png" alt="Imagem 7" style="width:100%; flex-shrink: 0; border: 5px solid black;">
  </div>
</div>
