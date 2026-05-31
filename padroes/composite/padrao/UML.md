# Padrão — Composite

> **Solução:** `Componente` é a interface comum para folhas (`Funcionario`) e compostos (`Departamento`). O cliente usa somente `Componente`, sem distinção — toda a hierarquia é percorrida de forma uniforme com um único método `exibirDetalhes()`.

```mermaid
classDiagram
    class Componente {
        <<interface>>
        +exibirDetalhes() void
    }

    class Funcionario {
        -String nome
        -double salario
        +Funcionario(String nome, double salario)
        +exibirDetalhes() void
    }

    class Departamento {
        -String nome
        -List~Componente~ componentes
        +Departamento(String nome)
        +adicionar(Componente c) void
        +remover(Componente c) void
        +exibirDetalhes() void
    }

    class Main {
        +main(String[] args) void
    }

    Componente <|.. Funcionario
    Componente <|.. Departamento
    Departamento o-- Componente
    Main ..> Departamento
    Main ..> Funcionario
```
