# Anti-Padrão — Estratégia

> **Problema:** as variações de comportamento são implementadas via **herança**, obrigando a criação de uma nova subclasse para cada operação e acoplando o comportamento ao tipo do objeto.

```mermaid
classDiagram
    class Calculadora {
        +calcular(int a, int b) int
    }

    class CalculadoraSubtracao {
        +calcular(int a, int b) int
    }

    class CalculadoraMultiplicacao {
        +calcular(int a, int b) int
    }

    class Main {
        +main(String[] args) void
    }

    Calculadora <|-- CalculadoraSubtracao
    Calculadora <|-- CalculadoraMultiplicacao
    Main ..> Calculadora
    Main ..> CalculadoraSubtracao
    Main ..> CalculadoraMultiplicacao
```
