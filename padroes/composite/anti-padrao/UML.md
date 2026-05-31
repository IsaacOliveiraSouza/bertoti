# Anti-Padrão — Composite

> **Problema:** `Departamento` mantém duas listas separadas — uma para `Funcionario` e outra para sub-`Departamento`. Não existe interface comum, então o cliente precisa conhecer e tratar cada tipo de forma diferente. Adicionar um novo tipo de nó exige modificar `Departamento`.

```mermaid
classDiagram
    class Funcionario {
        -String nome
        -double salario
        +Funcionario(String nome, double salario)
        +exibirDetalhes() void
    }

    class Departamento {
        -String nome
        -List~Funcionario~ funcionarios
        -List~Departamento~ subDepartamentos
        +Departamento(String nome)
        +adicionarFuncionario(Funcionario f) void
        +adicionarSubDepartamento(Departamento d) void
        +exibirDetalhes() void
    }

    class Main {
        +main(String[] args) void
    }

    Departamento --> Funcionario : funcionarios
    Departamento --> Departamento : subDepartamentos
    Main ..> Departamento
    Main ..> Funcionario
```
