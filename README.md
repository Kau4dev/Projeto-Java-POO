# Projeto-Java-POO

# 📋 Controle de Tarefas

Sistema em Java com persistência em SQLite para gerenciamento de tarefas, categorias e colaboradores. Desenvolvido com base em princípios de Programação Orientada a Objetos (POO).

## 🚀 Funcionalidades

- Cadastro e login de **Gerentes** e **Colaboradores**
- Gerenciamento de **tarefas** (criar, editar, excluir, visualizar)
- Associação de tarefas a um ou mais **colaboradores**
- Visualização de tarefas por **categoria**, **status** ou **colaborador**
- Gerenciamento de **categorias de tarefas**
- Interface **via terminal/console**

---

## 🗂️ Estrutura do Projeto

```
controle_tarefas/
├── database/
│   └── controle_tarefas.db         # Banco SQLite gerado automaticamente
├── lib/
│   └── sqlite-jdbc-3.49.1.0.jar    # Driver JDBC do SQLite
├── src/
│   ├── dao/                        # Acesso a dados (DAO)
│   ├── models/                     # Entidades (Tarefa, Colaborador, etc.)
│   ├── services/                   # Lógica de negócios
│   └── Main.java                   # Classe principal (entrada do sistema)
├── .classpath
└── README.md
```

---

## ⚙️ Pré-requisitos

- Java **17** ou superior
- [SQLite JDBC Driver](https://github.com/xerial/sqlite-jdbc) (já incluso no projeto)
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

---

## 💻 Como Executar

### ✅ Usando uma IDE (recomendado)

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Kau4dev/Projeto-Java-POO.git
   cd Projeto-Java-POO/controle_tarefas
   ```

2. **Abra o projeto na sua IDE preferida:**
   - Caso use Eclipse, o `.classpath` já reconhece as dependências automaticamente.
   - Em outras IDEs (IntelliJ, VS Code), adicione o `sqlite-jdbc-3.49.1.0.jar` manualmente ao classpath/módulo do projeto.

3. **Execute Main.java**  
   Localize `Main.java` dentro de `src/` e rode como aplicação Java.

4. **Banco de Dados**  
   O arquivo `controle_tarefas.db` será criado automaticamente na primeira execução, assim como todas as tabelas necessárias (via `DbSetup.criarTabelas()`).

---

### ✅ Passo a Passo — Resolver conexão com SQLite no VS Code

#### 📦 1. Adicionar o driver SQLite ao Java Runtime

- Pressione **Ctrl + Shift + P** para abrir a paleta de comandos.
- Digite e selecione: **Configurações de Runtime do Java**.
- Clique em **Editar configurações** ou **Configurar bibliotecas**.
- Clique em **Adicionar JAR externo** (ou nome semelhante).
- Selecione o arquivo:  
  `lib/sqlite-jdbc-3.49.1.0.jar`
- Confirme.

#### 📁 2. Marcar a pasta src como fonte do projeto

Se você não adicionou a src como source folder, o VS Code pode não compilar corretamente.

- Clique com o botão direito na pasta **src**.
- Selecione: **Adicionar como Pasta de Origem Java** (ou "Mark as Java Source Root").
- O VS Code agora reconhecerá a estrutura de pacotes e classes.

#### 🛠️ 3. Compilar e rodar

Após isso, o VS Code cuida da compilação automática, mas se quiser rodar manualmente:

- Vá até o arquivo **Main.java**.
- Clique com o botão direito e selecione **Run Java** (ou **Executar Java**).

#### ✅ Verifique o caminho do banco

No seu código, a conexão deve estar assim:

```java
String url = "jdbc:sqlite:database/controle_tarefas.db";
```
Confirme que a pasta `database/` e o arquivo `.db` existem na raiz do projeto.  
Se não existir, a aplicação criará um novo arquivo vazio ali.

#### 🧠 Dica extra

Para garantir que o `.jar` seja incluído automaticamente em projetos Java no VS Code, crie um arquivo `settings.json` em `.vscode/` com:

```json
{
  "java.project.referencedLibraries": [
    "lib/**/*.jar"
  ]
}
```
Assim, o VS Code sempre inclui os `.jar` da pasta `lib/`.

---

### 💡 Rodando pelo terminal (sem IDE)

1. **Compile os arquivos:**
   ```bash
   javac -cp "lib/sqlite-jdbc-3.49.1.0.jar" -d bin src/*.java src/models/*.java src/services/*.java src/dao/*.java
   ```

2. **Execute a aplicação:**
   ```bash
   java -cp "bin:lib/sqlite-jdbc-3.49.1.0.jar" Main
   ```
   > No Windows, troque `:` por `;` no classpath:
   > ```
   > java -cp "bin;lib/sqlite-jdbc-3.49.1.0.jar" Main
   > ```

---

### 🧠 Sobre o SQLite

- Não é necessário instalar ou iniciar o SQLite manualmente.
- O banco `controle_tarefas.db` será criado automaticamente na pasta `/database/` assim que a aplicação rodar.
- Todas as tabelas são criadas automaticamente pela classe `DbSetup`.

**Dica:**  
Se quiser visualizar ou editar o banco, utilize ferramentas como [DB Browser for SQLite](https://sqlitebrowser.org/) ou extensões para VS Code.

---

## 📌 Exemplo de uso

Ao iniciar a aplicação, será exibido o menu:

```
Controle de Tarefas
1 - Login
2 - Cadastro
3 - Sair
Digite sua escolha:
```

- **Após login como Gerente:**  
  - Gerencie colaboradores e tarefas  
  - Associe tarefas a colaboradores  
  - Visualize e edite tarefas e categorias

- **Após login como Colaborador:**  
  - Visualize suas tarefas atribuídas  
  - Atualize o status das tarefas

---

Sinta-se à vontade para contribuir, sugerir melhorias ou relatar problemas via issues!
