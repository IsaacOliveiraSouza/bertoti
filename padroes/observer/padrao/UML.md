# Padrão — Observer

> **Solução:** `Canal` depende apenas da interface `Inscrito`. Novos tipos de inscritos podem ser adicionados sem nenhuma modificação em `Canal` — basta implementar a interface.

```mermaid
classDiagram
    class Inscrito {
        <<interface>>
        +notificar(String video) void
    }

    class Usuario {
        -String nome
        +Usuario(String nome)
        +notificar(String video) void
    }

    class Canal {
        -List~Inscrito~ inscritos
        +inscrever(Inscrito i) void
        +desinscrever(Inscrito i) void
        +postarVideo(String titulo) void
    }

    class Main {
        +main(String[] args) void
    }

    Inscrito <|.. Usuario
    Canal o-- Inscrito
    Main ..> Canal
    Main ..> Usuario
```
